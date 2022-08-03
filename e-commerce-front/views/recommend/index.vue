<template>
	<div>
		<el-breadcrumb separator="/">
		  <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
		  <el-breadcrumb-item><a href="/recommend">营销</a></el-breadcrumb-item>
		  <el-breadcrumb-item><a href="/recommend">专题推荐</a></el-breadcrumb-item>
		</el-breadcrumb>
		
		<el-table :data="tableData">
			<el-table-column prop="id" label="编号" width="100">
			</el-table-column>
			<el-table-column prop="subjectName" label="专题名称">
			</el-table-column>
			<el-table-column label="是否推荐">
				<template slot-scope="scope">
					<el-switch
					 :active-value="1"
					 :inactive-value="0"
					 @change="handleChange(scope.row.id,scope.row.recomendStatus)"
					 v-model="scope.row.recomendStatus"
					></el-switch>
				</template>
			</el-table-column>
			<el-table-column prop="sort" label="排序">
			</el-table-column>
			<el-table-column label="状态" prop="recomendStatus">
				<template slot-scope="scope">
					<span v-if="scope.row.recomendStatus === 1 && scope.row.active === 1">推荐</span>
					<span v-else-if="scope.row.recomendStatus === 0 && scope.row.active === 1">不推荐</span>
					<span v-else-if="scope.row.active === 0">无效</span>
				</template>
			</el-table-column>
			<el-table-column label="操作">
				  <template slot-scope="scope">
					  <template v-if="scope.row.active === 1">
							<!-- <el-button
							size="mini"
							type="text"
							>关联商品</el-button> -->
							<el-button
							size="mini"
							type="text"
							@click="handleDelete(scope.row.id,0)">删除</el-button>
					  </template>
					 <template v-else>
						  <el-button
							size="mini"
							type="text"
							@click="handleDelete(scope.row.id,1)">恢复到有效</el-button>
					  </template>
				  </template>
			</el-table-column>
		</el-table>
	
	</div>
</template>

<script>
	export default {
		name: 'SubjectRecommend',
		data() {
			const module = '/sms-home-recommend-subject'
			return {
				url: {
					list: module + '/list',
					del: module + '/del',
					update: module + '/update'
				},
				tableData: []
			}
		},
		created() {
			this.getTableData()
		},
		methods:{
			getTableData() {
				this.get(this.url.list,{},response => {
					this.tableData = response
				})
			},
			handleDelete(id ,active) {
				const txt = active === 0 ? '确定真的要删除该数据吗?' : '确定真的要将数据恢复到有效吗?'
				this.$confirm(txt,'提示',{type: 'warning'}).then(()=>{
					this.post(this.url.del,{id: id, active: active}, () => {
						this.getTableData()
					})
				})	
			},
			handleChange(id,recomendStatus) {
				this.post(this.url.update,{id:id,recomendStatus:recomendStatus},()=>{})
			}
		}
	}
</script>

<style>
</style>
