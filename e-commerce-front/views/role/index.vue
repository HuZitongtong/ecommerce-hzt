<template>
	<div>
		<el-breadcrumb separator="/">
		  <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
		  <el-breadcrumb-item><a href="/role">权限</a></el-breadcrumb-item>
		  <el-breadcrumb-item><a href="/role">角色管理</a></el-breadcrumb-item>
		</el-breadcrumb>
		<div style="margin-top: 15px">
		  <el-form :inline="true" :model="listQuery" size="small" label-width="140px">
			<el-form-item label="输入搜索：">
			  <el-input  class="input-width" placeholder="角色名" clearable></el-input>
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
					<el-button size="mini" class="btn-add" @click="handleAdd()" style="margin-left: 20px" icon="el-icon-plus">
						添加</el-button>
		</el-card>
		<el-table :data="tableData">
			<el-table-column prop="id" label="编号" width="100">
			</el-table-column>
			<el-table-column prop="name" label="角色名">
			</el-table-column>
			<el-table-column label="分配">
				<template slot-scope="scope">
					<el-button
						size="mini"
						type="text"
						@click="handleRole(scope.row.id ,scope.row.name)">分配角色</el-button>
					<el-button
						size="mini"
						type="text"
						@click="handleResource(scope.row.id ,scope.row.name)">分配资源</el-button>
				</template>
			</el-table-column>
			<el-table-column prop="active" label="是否有效">
				<template slot-scope="scope">
					<span v-if="scope.row.active === 1">有效</span>
					<span v-else>无效</span>
				</template>
			</el-table-column>
			<el-table-column label="操作">
			  <template slot-scope="scope">
				  <template v-if="scope.row.active === 1">
					  <el-button
						size="mini"
						type="text"
						@click="handleUpdate(scope.row.id)">编辑</el-button>
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
		<el-dialog
			width="450px"
			:title="userDialog.title" 
			:visible.sync="userDialog.show"
			:close-on-click-modal="false">
			<component 
				v-if="userDialog.show"
				:is="userDialog.component"
				:show.sync="userDialog.show"
				:id="userDialog.id"
				@getTableData="getTableData"></component>
		</el-dialog>
	</div>
</template>

<script>
	export default {
		name: 'RoleList',
		data() {
			const module = '/ums_role'
			return {
				tableData: [],
				url:{
					list: module + '/list',
					del: module +'/del'
				},
				query: {
					name: ''
				},	
				userDialog: {
					show: false,
					title: '',
					component: () => import('./edit'),
					id: null
				}
			}
		},
		created() {
			this.getTableData()
		},
		methods: {
			getTableData() {
				this.get(this.url.list, this.query, response => {
					this.tableData = response
				})
			},
			//给角色分配用户
			handleRole(roleId,roleName) {
				 this.$router.push({
					 path: '/roleusers',
					 query: {
						 roleId: roleId,
						 roleName: roleName
					 }
				 })
			},
			handleResource(roleId,roleName) {
				this.$router.push({
					path:'/roleResource',
					query: {
						 roleId: roleId,
						 roleName: roleName
					}
				})
			},
			handleAdd() {
				this.userDialog.show = true
				this.userDialog.title = '添加角色'
			},
			handleUpdate(id) {
				this.userDialog.id = id
				this.userDialog.show = true
				this.userDialog.title = '修改角色'
			},
			handleDelete(id, active) {
				const txt = active === 0 ? '确定真的要删除该数据吗?' : '确定真的要将数据恢复到有效吗?'
				this.$confirm(txt,'提示',{type: 'warning'}).then(()=>{
					this.post(this.url.del,{id: id, active: active}, () => {
						this.getTableData()
					})
				})
			}
		}
	}
</script>

<style>
</style>
