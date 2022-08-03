<template>
	<div>
		<el-breadcrumb separator="/">
		  <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
		  <el-breadcrumb-item><a href="/advertise">营销</a></el-breadcrumb-item>
		  <el-breadcrumb-item>营销管理</a></el-breadcrumb-item>
		</el-breadcrumb>
		<div style="margin-top: 15px">
		  <el-form :inline="true" :model="listQuery" size="small" label-width="140px">
			<el-form-item label="输入搜索：">
			  <el-input class="input-width" v-model="query.name" placeholder="广告名称" clearable></el-input>
			</el-form-item>
			<el-form-item>
				<el-button
				  style="float:right"
				  type="primary"
				  @click="handleSearch()"
				  size="small">
				  查询搜索
				</el-button>
			</el-form-item>
		  </el-form>
		  <el-card class="operate-container" shadow="never">
		  			<i class="el-icon-tickets"></i>
		  			<span>数据列表</span>
		  			<el-button size="mini" class="btn-add" @click="handleAdd()" style="margin-left: 20px" icon="el-icon-plus">
		  				添加</el-button>
		  </el-card>
		</div>
		<el-table :data="tableData.records">
			<el-table-column prop="id" label="id" width="60">
			</el-table-column>
			<el-table-column prop="name" label="名称">
			</el-table-column>
			<el-table-column prop="type" label="广告位置">
				<template slot-scope="scope">
					 <span v-if="scope.row.type === 1">pc首页轮播</span>
					 <span v-else>app首页轮播</span>
				</template>
			</el-table-column>
			<el-table-column label="广告图片">
				<template slot-scope="scope">
					<el-image
						style="width: 100px; height: 100px"
						:src="img(scope.row.img)"
						fit="contain"></el-image>
				</template>
			</el-table-column>
			<el-table-column prop="clickCount" label="点击数">
			</el-table-column>
			<el-table-column prop="orderCount" label="下单数">
			</el-table-column>
			<el-table-column prop="status" label="上下线状态">
				<template slot-scope="scope">
					<span v-if="scope.row.status === 0">下线</span>
					<span v-else>上线</span>
				</template>
			</el-table-column>
			<el-table-column label="操作">
				<template slot-scope="scope">
					<span v-if="del === 0">
						<el-button
							size="mini"
						  type="text"
							@click="handleUpdate(scope.row.id)">修改</el-button>
							<el-button
							size="mini"
							type="text"
							@click="handleDelete(scope.row.id,0)">删除</el-button>
					</span>	
					<span v-else>
						<el-button
							size="mini"
						  type="text"
							@click="handleRelive(scope.row.id)">恢复</el-button>
							<el-button
							size="mini"
							type="text"
							@click="handleDelete(scope.row.id,0)">彻底删除</el-button>
					</span>
				</template>
			</el-table-column>
		</el-table>
	</div>
</template>

<script>
	export default {
		name:'AdvertiseList',
		data() {
			const module = '/sms-advertise'
			return {
				del: 0,
				url:{
					list: module + "/list"
				},
				tableData: [],
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
		methods: {
			getTableData() {
				this.get(this.url.list, this.query, response => {
					this.tableData = response
					// console.log(response)
				})
			},
			handleAdd() {
				this.$router.push({
					path: '/advertiseEdit'
				})
			},
			handleUpdate(id) {
				this.$router.push({
					path: '/advertiseEdit',
					query:{
						id:id
					}
				})
			},
			handleSearch(){
				this.query.pageNo = 1
				this.getTableData()
			}
		},
	}
</script>

<style>
</style>
