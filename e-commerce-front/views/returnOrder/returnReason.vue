<template>
	<div>
		<el-breadcrumb separator="/" style="margin-bottom: 15px;">
		  <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
		  <el-breadcrumb-item><a href="/order">订单</a></el-breadcrumb-item>
		  <el-breadcrumb-item>退货原因管理</a></el-breadcrumb-item>
		</el-breadcrumb>
		<el-card class="operate-container" shadow="never">
			<i class="el-icon-tickets"></i>
			<span>数据列表</span>
			<el-button size="mini" class="btn-add" @click="handleAdd()" style="margin-left: 20px" icon="el-icon-plus">
				添加</el-button>
		</el-card>
		<el-table :data="tableData">
			<el-table-column prop="id" label="编号" width="100">
			</el-table-column>
			<el-table-column prop="type" label="原因类型">
			</el-table-column>
			<el-table-column prop="active" label="是否可用">
				<template slot-scope="scope">
					<el-switch
					 :active-value="1"
					 :inactive-value="0"
					@change="handleNewStatusChange(scope.row)"
					v-model="scope.row.active">
					</el-switch>
				</template>
			</el-table-column>
			<el-table-column label="操作">
				<template slot-scope="scope">
					<el-button
						size="mini"
						type="text"
						@click="handleUpdate(scope.row.id)">编辑</el-button>
						<el-button
						size="mini"
						type="text"
						@click="handleDelete(scope.row.id)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>
		<el-dialog
			width="450px"
			:title="dialog.title" 
			:visible.sync="dialog.show"
			:close-on-click-modal="false">
			<component 
				v-if="dialog.show"
				:is="dialog.component"
				:show.sync="dialog.show"
				:id="dialog.id"
				:url="url.getone"
				@getTableData="getTableData"></component>
		</el-dialog>
	</div>
</template>

<script>
	export default {
		name: 'ReturnReason',
		data() {
			const module = '/oms-return-reason'
			return {
				url: {
					list: module + '/list',
					update: module + '/update'	,
					del: module + '/del'
				},
				tableData: [],
				dialog: {
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
				this.get(this.url.list,{}, response => {
					this.tableData = response
				})
			},
			handleAdd() {
				this.dialog.show = true
				this.dialog.title = '添加退款原因'
			},
			handleNewStatusChange(row) {
				this.post(this.url.update,row,() => {})
			},
			handleUpdate(id){
				this.dialog.show = true
				this.dialog.title = '修改退款原因'
				this.dialog.id = id
			},
			handleDelete(id) {
				const txt = '确定真的要删除该数据吗?'
				this.$confirm(txt,'提示',{type: 'warning'}).then(()=>{
					this.post(this.url.del,{id: id}, () => {
						this.getTableData()
					})
				})
			}
		}
	}
</script>

<style>
</style>
