<template>
	<div>
		<el-breadcrumb separator="/">
		  <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
		  <el-breadcrumb-item><a href="/order">订单</a></el-breadcrumb-item>
		  <el-breadcrumb-item>退货申请列表</a></el-breadcrumb-item>
		</el-breadcrumb>
		<div style="margin-top: 15px">
		  <el-form :inline="true" :model="listQuery" size="small" label-width="140px">
			<el-form-item label="输入搜索：">
			  <el-input  class="input-width" placeholder="服务单号" clearable></el-input>
			</el-form-item>
			<el-form-item>
				<el-button
				  style="float:right"
				  type="primary"
				  @click="handleSearchList()"
				  size="small">
				  查询搜索
				</el-button>
			</el-form-item>
		  </el-form>
		</div>
		<el-card class="operate-container" shadow="never">
					<i class="el-icon-tickets"></i>
					<span>数据列表</span>
		</el-card>
		<el-table :data="tableData.records">
			<el-table-column prop="id" label="服务单号" width="100">
			</el-table-column>
			<el-table-column prop="submitTime" label="申请时间">
				<template slot-scope="scope">{{scope.row.submitTime | formatTime}}</template>
			</el-table-column>
			<el-table-column prop="loginName" label="用户账号">
			</el-table-column>
			<el-table-column prop="returnAmount" label="退款金额">
				<template slot-scope="scope">￥{{scope.row.returnAmount}}</template>
			</el-table-column>
			<el-table-column prop="status" label="申请状态">
				<template slot-scope="scope">{{scope.row.status | formatStatus}}</template>
			</el-table-column>
			<el-table-column label="操作">
				<template slot-scope="scope">
					<el-button type="primary" plain @click="getDetail(scope.row.id)" size="mini">详情</el-button>
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
	</div>
</template>

<script>
	export default {
		name: 'ReturnOrder',
		data() {
			const module = '/oms-return-apply'
			return {
				url:{
					list: module + '/list'
				},
				tableData:[],
				query: {
					pageNo: 1, 
					pageSize: 9,
					name: ''
				},
			}
		},
		created() {
			this.getTableData()
		},
		filters: {
			formatStatus(value) {
			  if (value === 0) {
			    return '待处理';
			  } else if (value === 1) {
			    return '退货中';
			  } else if (value === 2) {
			    return '已完成'
			  } else {
			    return '已拒绝';
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
			getDetail(id) {
				this.$router.push({
					path:'/applyDetail',
					query:{
						id:id
					}
				})
			},
			getTableData() {
				this.get(this.url.list,this.query,response => {
					this.tableData = response
					// console.log(response)
				})
			}
		}
	}
</script>

<style>
</style>
