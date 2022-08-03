 <template>
	 <div>
		 <el-breadcrumb separator="/" style="margin-bottom: 15px;">
		   <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
		   <el-breadcrumb-item><a href="/category">商品</a></el-breadcrumb-item>
		   <el-breadcrumb-item><a href="/category">分类管理</a></el-breadcrumb-item>
		 </el-breadcrumb>
		 <el-card class="operate-container" shadow="never">
		 			<i class="el-icon-tickets"></i>
		 			<span>数据列表</span>
		 			<el-button size="mini" class="btn-add" @click="handleAdd(0,1)" style="margin-left: 20px" icon="el-icon-plus">
		 				添加</el-button>
		 </el-card>
		 <el-table 
			:data="tableData"
			row-key="id"
			>
			<el-table-column label="编号" prop="id"></el-table-column>
			<el-table-column label="分类名称" prop="name"></el-table-column>
			<el-table-column label="层级" prop="level"></el-table-column>
			<el-table-column label="是否有效">
				<template slot-scope="scope">
					<span v-if="scope.row.active === 1">有效</span>
					<span v-else>失效</span>
				</template>
			</el-table-column>
			<el-table-column label="设置" width="200px">
				<template slot-scope="scope">
					<template v-if="scope.row.active === 1">
						<el-button type="text" @click="gospu(scope.row.id,scope.row.name)" size="mini">属性列表</el-button>
						<el-button type="text" @click="gosku(scope.row.id,scope.row.name)" size="mini">参数列表</el-button>
					</template>
				</template>
			</el-table-column>
			<el-table-column width="257px" label="操作">
				<template slot-scope="scope">
					<template v-if="scope.row.active === 1">
						<el-button type="primary" plain @click="handleAdd(scope.row.id, scope.row.level + 1)" size="mini">添加下级</el-button>
						<el-button type="primary" plain @click="handleUpdate(scope.row.id)" size="mini">修改分类</el-button>
						<el-button type="danger" plain @click="del(scope.row.id,0)" size="mini">删除</el-button>
					</template>
					<template v-else>
						<el-button type="danger" plain @click="del(scope.row.id,1)" size="mini">恢复到有效</el-button>
					</template>
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
		 				:parentId="dialog.parentId"
		 				:level="dialog.level"
		 				@getTableData="getTableData"></component>
		 		</el-dialog>
	 </div>
</template>

<script>
	export default {
		name: "CategoryList",
		data() {
			const module = '/pms-category'
			return {
				tableData: [],
				url:{
					list: module + '/list',
					del: module + '/del'
				},
				dialog: {
					show: false,
					title: '',
					component: () => import('./edit'),
					id: null,
					parentId: null,
					level: null,
				}
			}
		},
		created() {
			this.getTableData()
		},
		methods: {
			getTableData() {
				this.get(this.url.list,this.query, response => {
					this.tableData = response
				})
			},
			handleAdd(parentId, level) {
				 this.dialog.parentId = parentId
				 this.dialog.level = level
				 this.dialog.id = null
				 this.dialog.title = '添加服装分类'
				 this.dialog.show = true
			},
			handleUpdate(id) {
				this.dialog.parentId = null
				this.dialog.level = null
				this.dialog.id = id
				this.dialog.title = '编辑服装分类'
				this.dialog.show = true
			},
			del(id, active) {
				const txt = active === 0 ? '确定真的要删除该数据吗?' : '确定真的要将数据恢复到有效吗?'
				this.$confirm(txt,'提示',{type: 'warning'}).then(()=>{
					this.post(this.url.del,{id: id, active: active}, () => {
						this.getTableData()
					})
				})
			},
			gospu(categoryId,categoryName) {
				this.$router.push({
					path: '/spu',
					query: {
						categoryId: categoryId,
						categoryName: categoryName
					}
				})
			},
			gosku(categoryId,categoryName) {
				this.$router.push({
					path: '/sku',
					query: {
						categoryId: categoryId,	
						categoryName: categoryName
					}
				})
			}
		}
				
	}
</script>

<style>
</style>
