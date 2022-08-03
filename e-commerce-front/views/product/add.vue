<template>
	<div>
		<el-breadcrumb separator="/" style="margin-bottom: 15px;">
			<el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
			<el-breadcrumb-item><a href="/productAdd">商品</a></el-breadcrumb-item>
			<el-breadcrumb-item><a href="/productAdd">添加商品</a></el-breadcrumb-item>
		</el-breadcrumb>
		<el-card shadow="never" style="width: 900px;">
			<el-steps :active="flag" align-center style="margin-bottom: 20px;">
				<el-step v-for="item in tabs" :title="item.title" :key="item.title"></el-step>
			</el-steps>
			<component 
				v-for='(item,index) in tabs'
				v-show="index === flag"
				:is="item.component"
				:key="item.title"
				@setForm="setForm"
				@save="save"
				@next="next"
				@prev="prev"></component>
		</el-card>
	</div>
</template>

<script>
	export default {
		name: 'ProductAdd',
		data(){
			const module = '/pms-product'
			return {
				url: {
					add: module + '/add'
				},
				flag: 0,
				tabs: [
					{ title: '填写商品信息', component: () => import('./components/base')},
					{ title: '商品图片及相册', component: () => import('./components/img')},
					{ title: '填写商品属性', component: () => import('./components/attribute')},
					// { title: '商品属性', component: () => import('./components/spu')},
					// { title: '关联SKU', component: () => import('./components/sku')},
					// { title: 'SKU库存', component: () => import('./components/stock')},
					{ title: '商品详情', component: () => import('./components/detail')},
					//{ title: '关联专题', component: () => import('./components/subject')}
				],
				skus: [],
				stocks: [],
				form: {	
				}
			}		
		},
		created() {
			
		},
		methods: {
			next() {
				this.flag++
			},
			prev() {
				this.flag--
			},
			setSkus(value) {
				this.skus = value
			},
			setStock(value) {
				this.stocks = value
			},
			setForm(item) {
				for(let key in item){
					this.form[key] = item[key]
				}
			},
			save() {
				console.log(this.form)
				this.post(this.url.add, this.form, response => {
					
				})
			}
		}
	}
</script>

<style>
</style>
