<template>
	<div>
		<el-breadcrumb separator="/" style="margin-bottom: 15px;">
			<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item><a href="/product">商品</a></el-breadcrumb-item>
			<el-breadcrumb-item><a href="/product">商品列表</a></el-breadcrumb-item>
		</el-breadcrumb>
		 <el-table 
			:data="tableData.records">
			<el-table-column label="编号" prop="id"></el-table-column>
			<el-table-column label="商品名称" prop="name"></el-table-column>
			<el-table-column label="商品图片">
				<template slot-scope="scope">
					<el-image
						style="width: 100px; height: 100px"
						:src="img(scope.row.img)"
						fit="contain"></el-image>
				</template>
			</el-table-column>
			<el-table-column label="价格" prop="price"></el-table-column>
			<el-table-column label="标签">
				<template slot-scope="scope">
					<p>上架：
					 <el-switch
					  :active-value="1"
					  :inactive-value="0"
						@change="handlePublishStatusChange(scope.row.id,scope.row)"
						v-model="scope.row.publishStatus">
					 </el-switch>
					</p>
					<p>新品：
					 <el-switch
					  :active-value="1"
					  :inactive-value="0"
						@change="handleNewStatusChange(scope.row.id,scope.row)"
						v-model="scope.row.newStatus">
					 </el-switch>
					</p>
					<p>热卖：
					 <el-switch
					  :active-value="1"
					  :inactive-value="0"
						@change="handleRecommendStatusChange(scope.row.id,scope.row)"
						v-model="scope.row.hotStatus">
					 </el-switch>
					</p>
				</template>
			</el-table-column>
			 <el-table-column label="SKU库存" width="100" align="center">
			  <template slot-scope="scope">
				<el-button type="primary" icon="el-icon-edit" @click="handleShowSkuEditDialog(scope.row.id)" circle></el-button>
			  </template>
			</el-table-column>
			<el-table-column width="150px">
				<template slot-scope="scope">
					<template v-if="scope.row.active === 1">
						<el-button type="primary" plain @click="update(scope.row.id)" size="mini">编辑</el-button>
						<el-button type="danger" plain @click="del(scope.row.id,0)" size="mini">删除</el-button>
					</template>
					<template v-else>
						<el-button type="primary" plain @click="update(scope.row.id)" size="mini">编辑</el-button>
					</template>
				</template>
			</el-table-column>
		</el-table>
		<el-pagination
			background
			:current-page.sync="query.pageNo"
			layout="prev, pager, next"
			:page-count="tableData.pages"
			@current-change="getTableData">
		</el-pagination>
	</div>
</template>

<script>
	export default {
		name: 'ProductList',
		data() {
			const module = '/pms-product'
			return {
				tableData: [],
				url:{
					list: module + '/list',
					del: module +'/del',
					publish: module + '/publish'
				},
				query: {
					pageNo: 1,
					pageSize: 8
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
				})
			}
		}
	}
</script>

<style>
</style>
