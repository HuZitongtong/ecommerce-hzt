<template>
	<div>
		<el-breadcrumb separator="/" style="margin-bottom: 15px;">
			<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item><a href="/order">订单</a></el-breadcrumb-item>
			<el-breadcrumb-item>订单详情</a></el-breadcrumb-item>
		</el-breadcrumb>
		<el-steps :active="order.status" finish-status="success" align-center>
			<el-step v-for="item in tabs" :title="item.title" :key="item.title"></el-step>
		</el-steps>
		<!-- <el-steps :active="formatStepStatus(order.status)" finish-status="success" align-center>
		  <el-step title="提交订单" :description="formatTime(order.createTime)"></el-step>
		  <el-step title="支付订单" :description="formatTime(order.paymentTime)"></el-step>
		  <el-step title="平台发货" :description="formatTime(order.deliveryTime)"></el-step>
		  <el-step title="确认收货" :description="formatTime(order.receiveTime)"></el-step>
		  <el-step title="完成评价" :description="formatTime(order.commentTime)"></el-step>
		</el-steps> -->
		<el-card shadow="never" style="margin-top: 15px">
			<div class="operate-container">
			  <i class="el-icon-warning color-danger" style="margin-left: 20px"></i>
			  <span class="color-danger">当前订单状态：{{order.status | formatStatus}}</span>
			  <div class="operate-button-container" v-show="order.status===0">
			    <el-button size="mini" @click="showUpdateReceiverDialog">修改收货人信息</el-button>
			    <el-button size="mini">修改商品信息</el-button>
			    <el-button size="mini" @click="showUpdateMoneyDialog">修改费用信息</el-button>
			    <el-button size="mini" @click="showMessageDialog">发送站内信</el-button>
			    <el-button size="mini" @click="showCloseOrderDialog">关闭订单</el-button>
			    <el-button size="mini" @click="showMarkOrderDialog">备注订单</el-button>
			  </div>
			  <div class="operate-button-container" v-show="order.status===1">
			    <el-button size="mini" @click="showUpdateReceiverDialog">修改收货人信息</el-button>
			    <el-button size="mini" @click="showMessageDialog">发送站内信</el-button>
			    <el-button size="mini">取消订单</el-button>
			    <el-button size="mini" @click="showMarkOrderDialog">备注订单</el-button>
			  </div>
			  <div class="operate-button-container" v-show="order.status===2||order.status===3">
			    <el-button size="mini" @click="showLogisticsDialog">订单跟踪</el-button>
			    <el-button size="mini" @click="showMessageDialog">发送站内信</el-button>
			    <el-button size="mini" @click="showMarkOrderDialog">备注订单</el-button>
			  </div>
			  <div class="operate-button-container" v-show="order.status===4">
			    <el-button size="mini" @click="handleDeleteOrder">删除订单</el-button>
			    <el-button size="mini" @click="showMarkOrderDialog">备注订单</el-button>
			  </div>
			</div>
			<div style="margin-top: 20px;">
				<i class="" style="margin-left: 20px"></i>
				  <span>基本信息</span> 
			</div>
			<div class="table-layout">
				<el-row>
					<el-col :span="4" class="table-cell-title">订单编号</el-col>
					<el-col :span="4" class="table-cell-title">发货单流水号</el-col>
					<el-col :span="4" class="table-cell-title">用户账号</el-col>
					<el-col :span="4" class="table-cell-title">支付方式</el-col>
					<el-col :span="4" class="table-cell-title">订单来源</el-col>
					<!-- <el-col :span="4" class="table-cell-title">订单类型</el-col> -->
				</el-row>
				<el-row>
				  <el-col :span="4" class="table-cell">{{order.orderSn}}</el-col>
				  <el-col :span="4" class="table-cell">暂无</el-col>
				  <el-col :span="4" class="table-cell">{{order.loginName}}</el-col>
				  <el-col :span="4" class="table-cell">{{order.payType | formatPayType}}</el-col>
				  <el-col :span="4" class="table-cell">{{order.sourceType | formatSourceType}}</el-col>
				  <!-- <el-col :span="4" class="table-cell">{{order.orderType | formatOrderType}}</el-col> -->
				</el-row>
				<el-row>
				  <el-col :span="4" class="table-cell-title">配送方式</el-col>
				  <el-col :span="4" class="table-cell-title">物流单号</el-col>
				  <el-col :span="4" class="table-cell-title">自动确认收货时间</el-col>
				  <el-col :span="4" class="table-cell-title">订单可得优币</el-col>
				  <el-col :span="4" class="table-cell-title">订单可得成长值</el-col>
				</el-row>
				<el-row>
					<el-col :span="4" class="table-cell">{{order.deliveryCompany | formatNull}}</el-col>
					<el-col :span="4" class="table-cell">{{order.deliverySn | formatNull}}</el-col>
					<el-col :span="4" class="table-cell">{{order.autoConfirmDay}}15天</el-col>
					<el-col :span="4" class="table-cell">{{order.integration}}0</el-col>
					<el-col :span="4" class="table-cell">{{order.growth}}0</el-col>
				</el-row>
				<div style="margin: 20px">
				  合计：<span class="color-danger">￥{{order.totalAmount}}</span>
				</div>
			</div>
			<div style="margin-top: 20px">
				<i class="" style="margin-left: 20px"></i>
				 <span>收货人信息</span>
			</div>
			<div class="table-layout">
				<el-row>
				  <el-col :span="6" class="table-cell-title">收货人</el-col>
				  <el-col :span="6" class="table-cell-title">手机号码</el-col>
				  <el-col :span="6" class="table-cell-title">邮政编码</el-col>
				  <el-col :span="6" class="table-cell-title">收货地址</el-col>
				</el-row>
				<el-row>
				  <el-col :span="6" class="table-cell">{{order.receiverName}}</el-col>
				  <el-col :span="6" class="table-cell">{{order.receiverPhone}}</el-col>
				  <el-col :span="6" class="table-cell">{{order.receiverPostCode}}</el-col>
				  <el-col :span="6" class="table-cell">{{order | formatAddress}}</el-col>
				</el-row>
			</div>
			<div style="margin-top: 20px">
				<i class="" style="margin-left: 20px"></i>
				 <span>商品信息</span>
			</div>
			<div class="table-layout">
				<el-table :data="productTable" border>
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
			<div style="margin-top: 20px">
				<i class="" style="margin-left: 20px"></i>
				 <span>操作信息</span>
			</div>
		</el-card>
	</div>
	
</template>

<script>
	export default {
		name: 'OrderDetail',
		data() {
			const module = '/oms-order'
			return {
				url: {
					getone: module + '/getone',
					getProduct: module + '/getProduct',
				},
				id: '',
				orderStatus: 0,
				tabs: [
					{title:'提交订单'},
					{title:'支付订单'},
					{title:'平台发货'},
					{title:'确认收货'},
					{title:'完成评价'},
				],
				productTable:[],
				order:[],
			}
		},
		created() {
			this.id = this.$route.query.orderId
			this.getone()
			this.getProductTable()
		},
		filters:{
			 formatStatus(value) {
			   if (value === 1) {
			     return '待发货';
			   } else if (value === 2) {
			     return '已发货';
			   } else if (value === 3) {
			     return '已完成';
			   } else if (value === 4) {
			     return '已关闭';
			   } else if (value === 5) {
			     return '无效订单';
			   } else {
			     return '待付款';
			   }
			 },
			 formatPayType(value) {
			   if (value === 1) {
			     return '支付宝';
			   } else if (value === 2) {
			     return '微信';
			   } else {
			     return '未支付';
			   }
			 },
			 formatSourceType(value) {
			   if (value === 1) {
			     return 'APP订单';
			   } else {
			     return 'PC订单';
			   }
			 },
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
			 formatAddress(order) {
			   let str = order.receiverProvince;
			   if (order.receiverCity != null) {
			     str += "  " + order.receiverCity;
			   }
			   str += "  " + order.receiverRegion;
			   str += "  " + order.receiverDetailAddress;
			   return str;
			 },
		},
		methods: {
			getone(){
				this.get(this.url.getone,{id: this.id},response => {
					this.order = response
				})
			},
			getProductTable() {
				this.get(this.url.getProduct,{id:this.id},response => {
					console.log(response)
					// for(let i = 0; i < response.length; i++ ){
					// 	response[i].productAmount = response[i].productPrice*response[i].productNum
					// }
					this.productTable = response
				})
			},
		}
	}
</script>

<style lang="less">
	.color-danger{
		color: #fc0107;
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
