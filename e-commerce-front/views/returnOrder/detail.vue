<template>
	<div>
		<el-breadcrumb separator="/">
		  <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
		  <el-breadcrumb-item><a href="/order">订单</a></el-breadcrumb-item>
		  <el-breadcrumb-item>退货详情</a></el-breadcrumb-item>
		</el-breadcrumb>
		<div class="detail-container">
			<el-card shadow="never">
				<span class="font-title-medium">退货商品</span>
				<div class="table-layout">
					<el-table :data="productList" border>
						<el-table-column label="商品图片">
							<template slot-scope="scope">
								<el-image
									style="width: 100px; height: 100px"
									:src="img(scope.row.img)"
									fit="contain"></el-image>
							</template>
						</el-table-column>
						<el-table-column label="商品名称" prop="productName"></el-table-column>
						<el-table-column label="商品价格" prop="productPrice"></el-table-column>
						<el-table-column label="属性" prop="productAttr">
							<template slot-scope="scope">
							  {{scope.row.productAttr | formatProductAttr}}
							</template>
						</el-table-column>
						<el-table-column label="数量" prop="productNum"></el-table-column>
						<el-table-column label="小计" prop="productAmount">
							<template slot-scope="scope">
							  ￥{{scope.row.productPrice*scope.row.productNum}}
							</template>
						</el-table-column>
					</el-table>
				</div>
			</el-card>
			<el-card shadow="never" class="standard-margin">
				 <span class="font-title-medium">服务单信息</span>
				<div class="form-container-border">
					<el-row>
					  <el-col :span="6" class="form-border form-left-bg font-small">服务单号</el-col>
					  <el-col class="form-border font-small" :span="18">{{applyForm.id}}</el-col>
					</el-row>
					<el-row>
					  <el-col class="form-border form-left-bg font-small" :span="6">申请状态</el-col>
					  <el-col class="form-border font-small" :span="18">{{applyForm.status | formatStatus}}</el-col>
					</el-row>
					<el-row>
					  <el-col :span="6" class="form-border form-left-bg font-small" style="height:50px;line-height:30px">订单编号
					  </el-col>
					  <el-col class="form-border font-small" :span="18" style="height:50px">
					    {{applyForm.orderSn}}
					    <el-button type="text" size="small" @click="handleViewOrder">查看</el-button>
					  </el-col>
					</el-row>
					<el-row>
					  <el-col class="form-border form-left-bg font-small" :span="6">申请时间</el-col>
					  <el-col class="form-border font-small" :span="18">{{applyForm.submitTime | formatTime}}</el-col>
					</el-row>
					<el-row>
					  <el-col class="form-border form-left-bg font-small" :span="6">用户账号</el-col>
					  <el-col class="form-border font-small" :span="18">{{applyForm.loginName}}</el-col>
					</el-row>
					<el-row>
					  <el-col class="form-border form-left-bg font-small" :span="6">退货原因</el-col>
					  <el-col class="form-border font-small" :span="18">{{applyForm.returnReason}}</el-col>
					</el-row>
					<el-row>
					  <el-col class="form-border form-left-bg font-small" :span="6">问题描述</el-col>
					  <el-col class="form-border font-small" :span="18">{{applyForm.reasonDetail}}</el-col>
					</el-row>
				</div>
				<div class="form-container-border">
					<el-row>
					  <el-col class="form-border form-left-bg font-small" :span="6">订单金额</el-col>
					  <el-col class="form-border font-small" :span="18">￥{{totalAmount}}</el-col>
					</el-row>
					<el-row>
					  <el-col class="form-border form-left-bg font-small" :span="6" style="height:52px;line-height:32px">确认退款金额
					  </el-col>
					  <el-col class="form-border font-small" style="height:52px" :span="18">
					    ￥
					    <el-input size="small" v-model="totalAmount"
					              :disabled="applyForm.status!==0"
					              style="width:200px;margin-left: 10px"></el-input>
					  </el-col>
					</el-row>
					 <div v-show="applyForm.status!==3">
						 <el-row>
						   <el-col class="form-border form-left-bg font-small" :span="6">收货人姓名</el-col>
						   <el-col class="form-border font-small" :span="18">{{addressForm.name}}</el-col>
						 </el-row>
						 <el-row>
						   <el-col class="form-border form-left-bg font-small" :span="6">所在区域</el-col>
						   <el-col class="form-border font-small" :span="18">{{addressForm.province+" "+addressForm.city+" "+addressForm.region}}</el-col>
						 </el-row>
						 <el-row>
						   <el-col class="form-border form-left-bg font-small" :span="6">详细地址</el-col>
						   <el-col class="form-border font-small" :span="18">{{addressForm.detailAddress}}</el-col>
						 </el-row>
						 <el-row>
						   <el-col class="form-border form-left-bg font-small" :span="6">联系电话</el-col>
						   <el-col class="form-border font-small" :span="18">{{addressForm.phone}}</el-col>
						 </el-row>
					 </div>
				</div>
				<div style="margin-top:15px;text-align: center" v-show="applyForm.status===0">
				  <el-button type="primary" size="small" @click="handleUpdateStatus(1)">确认退货</el-button>
				  <el-button type="danger" size="small" @click="handleUpdateStatus(3)">拒绝退货</el-button>
				</div>
				<div style="margin-top:15px;text-align: center" v-show="applyForm.status===1">
				  <el-button type="primary" size="small" @click="handleUpdateStatus(2)">确认收货</el-button>
				</div>
			</el-card>
		</div>	 
	</div>
</template>

<script>
	export default {
		name: 'ApplyDetail',
		data() {
			const module = '/oms-return-apply'
			return {
				url: {
					getDetail: module + '/getDetail',
					updateStatus: module + '/updateStatus'
				},
				id:null,
				orderReturnApply:[],
				productList:[],
				applyForm: {
					id:'',
					status:'',
					submitTime:'',
					loginName:'',
					returnReason:'',
					reasonDetail:'',
					orderSn:'',
					orderId:''
				},
				addressForm:{
					id:'',
					name:'',
					addressName:'',
					phone:'',
					province:'',
					city:'',
					region:'',
					detailAddress:'',
					sendStatus:'',
				},
				totalAmount:'29.9',
				
			}
		},
		created() {
			this.id = this.$route.query.id
			this.getDetail()
		},
		filters:{
			formatProductAttr(value){
			  if(value==null){
			    return '';
			  }else{
			    let attr = JSON.parse(value);
			    let result='';
			    for(let i=0;i<attr.length;i++){
			      result+=attr[i].name;
			      result+=":";
			      result+=attr[i].value;
			      result+=";";
			    }
			    return result;
			  }
			},
			formatStatus(status) {
			  if (status === 0) {
			    return "待处理";
			  } else if (status === 1) {
			    return "退货中";
			  } else if (status === 2) {
			    return "已完成";
			  } else {
			    return "已拒绝";
			  }
			},
			formatTime(time) {
			  if (time == null || time === '') {
			    return 'N/A';
			  }
			  let date = new Date(time);
			  // return formatDate(date, 'yyyy-MM-dd hh:mm:ss')
			  let Y = date.getFullYear() + '-';
			  let M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
			  let D = date.getDate() + ' ';
			  let h = date.getHours() + ':';
			  let m = date.getMinutes() + ':';
			  let s = date.getSeconds();
			  return Y+M+D+h+m+s;
			},
		},
		methods: {
			handleUpdateStatus(status) {
				this.post(this.url.updateStatus,{id:this.applyForm.id, status:status},() => {
					this.$router.push('/returnOrder')
				})
			},
			getDetail() {
				this.get(this.url.getDetail,{id:this.id},response => {
					console.log(response)
					this.productList = response.productList
					this.applyForm = response.applyList[0]
					this.addressForm = response.addressList[0]
				})
			},
			handleViewOrder() {
				this.$router.push({
					path: '/orderDetail',
					query: {
						orderId: this.applyForm.orderId,
					}
				})
			}
		}
	}
</script>

<style scoped lang="less">
	.standard-margin{
		margin-top: 15px;
	}
	.detail-container{
		margin-top: 15px;
	}
	.form-container-border {
		.el-row {
		height: 30px;
		  line-height: 30px;
		  color: #666666;
		  font-size: 14px;
		  font-weight: 400;
		  &:first-child {
			font-family: 400;
		  }
		 //  &:last-child {
			// border-right: none;
		 //  }
		}
		.el-col {
		  height: 35px;
		  line-height: 35px;
		  border-bottom: 1px solid #e4e7ed;
		  border-right: 1px solid #e4e7ed;
		  border-top: 1px solid #e4e7ed;
		  border-left: 1px solid #e4e7ed;
		  align-items: center;
		  text-align: center;
		  &:first-child{
			  background-color: #eef6fa;
		  }
		  // &:last-child {
		  //   border-right: none;
		  // }
		}
	}
	.table-layout {
		margin-top: 20px;
		.table-cell-title {	
			background-color: #eef6fa;
			height: 42px;
			  line-height: 42px;
			  color: #666666;
			  font-size: 14px;
			  font-weight: 400;
			 //  &:first-child {
				// font-family: 400;
			 //  }
			 //  &:last-child {
				// border-right: none;
			 //  }
		}
		.table-cell {
			height: 42px;
			  line-height: 42px;
			  color: #666666;
			  font-size: 14px;
			  font-weight: 400;
			 //  &:first-child {
				// font-family: 400;
			 //  }
			 //  &:last-child {
				// border-right: none;
			 //  }
		}
		.el-col {
		      height: 55px;
		      line-height: 55px;
		      border-bottom: 1px solid #e4e7ed;
		      border-right: 1px solid #e4e7ed;
		      align-items: center;
		      text-align: center;
		      // &:last-child {
		      //   border-right: none;
		      // }
		    }
		.border-style() {
		  background: #ffffff;
		  border: 1px solid #ececee;
		}
	}
</style>
