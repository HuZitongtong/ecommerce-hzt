<template>
	<div>
		<el-breadcrumb separator="/" style="margin-bottom: 15px;">
			<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item><a href="/order">订单</a></el-breadcrumb-item>
			<el-breadcrumb-item><a href="/order">订单列表</a></el-breadcrumb-item>
		</el-breadcrumb>
		<el-table
			:data="tableData.records">
			<el-table-column label="编号" prop="id" width="60"></el-table-column>
			<el-table-column label="订单编号" prop="orderSn" width="180"></el-table-column>
			<el-table-column label="提交时间" prop="createTime" width="180">
				<template slot-scope="scope">{{scope.row.createTime | formatTime}}</template>
			</el-table-column>
			<el-table-column label="用户名" prop="loginName"></el-table-column>
			<el-table-column label="订单金额" prop="totalAmount"></el-table-column>
			<el-table-column label="支付方式" prop="payType">
				 <template slot-scope="scope">{{scope.row.payType | formatPayType}}</template>
			</el-table-column>
			<el-table-column label="订单来源" prop="sourceType">
				<template slot-scope="scope">{{scope.row.sourceType | formatSourceType}}</template>
			</el-table-column>
			<el-table-column label="订单状态" prop="status">
				<template slot-scope="scope">{{scope.row.status | formatStatus}}</template>
			</el-table-column>
			<el-table-column label="操作" width="200">
				<template slot-scope="scope">
				  <el-button
				    size="mini"
				    @click="handleViewOrder(scope.row.id, scope.row)"
				  >查看订单</el-button>
				  <el-button
				    size="mini"
				    @click="handleCloseOrder(scope.$index, scope.row)"
				    v-show="scope.row.status===0">关闭订单</el-button>
				  <el-button
				    size="mini"
				    @click="handleDeliveryOrder(scope.row.id, scope.row)"
				    v-show="scope.row.status===1">订单发货</el-button>
				  <el-button
				    size="mini"
				    @click="handleViewLogistics(scope.row.id, scope.row)"
				    v-show="scope.row.status===2||scope.row.status===3">订单跟踪</el-button>
				  <el-button
				    size="mini"
				    type="danger"
				    @click="handleDeleteOrder(scope.$index, scope.row)"
				    v-show="scope.row.status===4">删除订单</el-button>
				</template>
			</el-table-column>
		</el-table>
		<div class="pagination">
			<el-pagination
			  background
			  layout="prev, pager, next" 
			  :current-page.sync="query.pageNo"
			  :page-count="tableData.pages"
			  @current-change="getTableData()"
			  >
			</el-pagination>
		</div>
		<el-dialog
			width="450px"
			:close-on-click-modal = "false"
			:title="dialog.title"
			:visible.sync="dialog.show">
			<component
				:is="dialog.component"
				v-if="dialog.show"
				:show.sync="dialog.show"
				:id="dialog.id"></component>
		</el-dialog>
	</div>
</template>

<script>
	export default {
		name: 'OrderList',
		data() {
			const module = '/oms-order'
			return {
				tableData: [],
				url: {
					list: module + '/list'
				},
				query: {
					pageNo: 1, 
					pageSize: 9,
					name: ''
				},
				dialog: {
					title: '',
					show: false,
					id: null,
					component: () => import('./components/logisticsDialog')
				}
			}
		},
		filters: {
			formatCreateTime(time) {
			  let date = new Date(time);
			  return formatDate(date, 'yyyy-MM-dd hh:mm:ss')
			},
		},
		created() {
			this.getTableData()
		},
		filters: {
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
			formatSourceType(value) {
			  if (value === 1) {
			    return 'APP订单';
			  } else {
			    return 'PC订单';
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
			getTableData(){
				this.get(this.url.list,this.query,response => {
					console.log(response)
					this.tableData = response
				})
			},
			//跳转至订单详情
			handleViewOrder(orderId, row) {
				this.$router.push({
					path: '/orderDetail',
					query: {
						orderId: orderId,
					}
				})
			},
			//发货列表
			handleDeliveryOrder(orderId, row) {
				this.$router.push({
					path:'/deliverOrderList',
					query: {
						orderId: orderId
					}
				})
			},
			//订单跟踪
			handleViewLogistics(id,row) {
				this.dialog.id = id
				this.dialog.title = '订单跟踪'
				this.dialog.show = true
			},
 		}
	}
</script>

<style>
</style>
