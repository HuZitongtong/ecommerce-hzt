<template>
	<div>
		<el-breadcrumb separator="/" style="margin-bottom: 15px;">
			<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item><a href="/order">订单</a></el-breadcrumb-item>
			<el-breadcrumb-item>发货列表</a></el-breadcrumb-item>
		</el-breadcrumb>
		<el-card class="operate-container" shadow="never">
			<i class="el-icon-tickets"></i>
			<span>发货列表</span>
		</el-card>
		<el-table 
			:data="tableData" 
			ref="orderList"
			:rules="rules"
			border style="margin-bottom: 20px;">
			<el-table-column label="订单编号" prop="orderSn" width="180"></el-table-column>
			<el-table-column label="收货人" prop="receiverName" width="180"></el-table-column>
			<el-table-column label="手机号码" prop="receiverPhone" width="180"></el-table-column>
			<el-table-column label="邮政编码" prop="receiverPostCode" width="180"></el-table-column>
			<el-table-column label="收货人地址" prop="address" width="180"></el-table-column>
			<el-table-column label="配送方式" width="180" prop="deliveryCompany">
				<template slot-scope="scope">
				  <el-select placeholder="请选择物流公司"
				             v-model="scope.row.deliveryCompany"
				             size="small">
				    <el-option v-for="item in companyOptions"
				               :key="item"
				               :label="item"
				               :value="item">
				    </el-option>
				  </el-select>
				</template>
			</el-table-column>
			<el-table-column label="物流单号" prop="deliverySn" width="180">
				<template slot-scope="scope">
				  <el-input size="small" v-model="scope.row.deliverySn"></el-input>
				</template>
			</el-table-column>
		</el-table>
		<div class="dialog-footer">
			<el-button type="primary" @click="save">确 定</el-button>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'deliverOrderList',
		data() {
			const module = '/oms-order'
			return {
				url: {
					getone: module + '/getone',
					update: module + '/update'
				},
				tableData: [],
				orderId: '',
				companyOptions: ['顺丰快递','圆通快递','中通快递','韵达快递'],
				rules: {
					deliveryCompany: [
						{ required: true, message: '选择物流公司', trigger: 'blur' },
					]
					
				}
			}
		},
		created() {
			this.orderId = this.$route.query.orderId
			// console.log(this.orderId)
			this.getTableData()
		},
		methods: {
			getTableData() {
				this.get(this.url.getone,{id:this.orderId},response => {
					let address = response.receiverProvince + response.receiverCity + response.receiverRegion
									+ response.receiverDetailAddress
					response.address = address
					this.tableData.push(response)
				})
			},
			save() {
				//修改状态为已发货
				let deliveryCompany = this.tableData[0].deliveryCompany
				let deliverySn = this.tableData[0].deliverySn
				this.$confirm('是否要进行发货操作？','提示',{type: 'warning'}).then(()=>{
					this.post(this.url.update,
					{id: this.orderId, status:2, deliveryCompany:deliveryCompany, deliverySn:deliverySn}, () => {
						this.tableData = []
						this.getTableData()
					})
				})
				
			},
		}
	}
</script>

<style lang="less">
</style>
