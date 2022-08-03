<template>
	<div>
		<el-breadcrumb separator="/">
		  <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
		  <el-breadcrumb-item><a href="/user">权限</a></el-breadcrumb-item>
		  <el-breadcrumb-item><a href="/user">用户管理</a></el-breadcrumb-item>
		</el-breadcrumb>
		<div style="margin-top: 15px">
		  <el-form :inline="true" :model="listQuery" size="small" label-width="140px">
			<el-form-item label="输入搜索：">
			  <el-input class="input-width" v-model="query.name" placeholder="昵称" clearable></el-input>
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
		</div>
		  <el-card class="operate-container" shadow="never">
			<i class="el-icon-tickets"></i>
			<span>数据列表</span>
			<el-button size="mini" class="btn-add" @click="handleAdd()" style="margin-left: 20px" icon="el-icon-plus">
				添加</el-button>
		  </el-card>
	    <el-table :data="tableData.records">
	      <el-table-column prop="id" label="id" width="60">
	      </el-table-column>
	      <el-table-column prop="loginName" label="登录名" width="120">
	      </el-table-column>
	      <el-table-column prop="nickyName" label="昵称">
	      </el-table-column>
		  <el-table-column prop="phone" label="手机号">
		  </el-table-column>
		  <el-table-column prop="email" label="邮箱">
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
					 <!-- <el-button
						size="mini"
						type="text"
						@click="handleRole">分配角色</el-button> -->
					  <el-button
					  	size="mini"
					    type="text"
						@click="handleUpdate(scope.row.id)">修改</el-button>
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
			:title="userDialog.title" 
			:visible.sync="userDialog.show">
			<component 
				v-if="userDialog.show"
				:is="userDialog.component"
				:show.sync="userDialog.show"
				:id = "userDialog.id"
				@getTableData = "getTableData"></component>
		</el-dialog>
		
	</div>
</template>

<script>
	
	export default {
		name: 'UserList',
		data() {
		  const item = {
		   
		  };
		  return {
		    tableData: [],
			query: {
				pageNo: 1, 
				pageSize: 9,
				name: ''
			},
			userDialog: {
				show: false,
				title: '',
				id: null,
				//此处为动态组件，无需注册，引用方式类似路由
				component: () => import("./edit.vue")
			}
		  }
		},
		created() {
			//一开始就运行
			this.getTableData()
		},
		mounted() {
			//渲染完了再运行
		},
		methods: {
			getTableData() {
				this.get('/ums_user/list',this.query,response => {
					this.tableData = response
				})			
			},
			handleSearch() {
				this.query.pageNo = 1
				this.getTableData()
			},
			handleRole() {
				
			},
			handleAdd() {
				this.userDialog.id = null
				this.userDialog.show = true
				this.userDialog.title = '添加用户'
			},
			handleUpdate(id) {
				this.userDialog.id = id
				this.userDialog.show = true
				this.userDialog.title = '修改用户'
			},
			handleDelete(id, active) {
				const txt = active === 0 ? '确定真的要删除该数据吗?' : '确定真的要将数据恢复到有效吗?'
				this.$confirm(txt,'提示',{type: 'warning'}).then(()=>{
					this.post('/ums_user/delete',{id: id, active: active}, () => {
						this.getTableData()
					})
				})
				// this.post('ums_user/delete', {id: id, acitve: acitve}, () => {
				// 	//删除后刷新数据
				// 	this.getTableData()
				// })
			}
		}
	}
</script>

<style>
</style>
