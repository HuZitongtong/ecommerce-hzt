<template>
	<div>
		<el-form ref="form" :model="form" label-width="120px" :rules="rules">
			<el-form-item label="商品分类" prop="categoryId">
				<el-cascader
					placeholder="生成商品属性规格"
					v-model="form.categoryId"
					:options="this.categorys"
					:props="{label: 'name', value: 'id'}" 
					@change="handleChange"
					></el-cascader>
			</el-form-item>
			<el-form-item label="商品属性">
				<el-card shadow="never">
					<el-form-item v-for="item in form.spus" :key="item.name" :label="item.name">
						<el-input v-model="item.value"></el-input>
					</el-form-item>
				</el-card>
			</el-form-item>
			<el-form-item label="商品规格">
				<el-card shadow="never">
					<el-form-item 
						v-for="item in form.skus" 
						:key="item.id" 
						:label="item.name">
						<template v-if="item.addType === 1">
							<el-input v-model="item.addval"></el-input>
							<el-button size="small" @click="addItem(item)">增加</el-button>
						</template>
						<el-checkbox-group v-if="item.addType === 0" v-model="item.value">
							<el-checkbox v-for="opt in item.inputList.split('\n')" :key="opt" :label="opt">
							</el-checkbox>
						</el-checkbox-group>
						<div v-else>
							<el-checkbox-group v-model="item.value">
								<div v-for="(opt,index) in item.inputList.split('\n')">
									<el-checkbox :label="opt" :key="opt"></el-checkbox>
									<el-button type="text" @click="delItem(item,index)" style="margin-left: 5px;">删除</el-button>
								</div>
							</el-checkbox-group>
						</div>
					</el-form-item>
					<div>
						<el-button type="primary" size="small" @click="checkAttr">确认参数</el-button>
						<el-table :data="form.stocks" border>
							<el-table-column v-for="item in form.skus" :key="item.id" :label="item.name">
								<template slot-scope="scope">
									<template v-for="(entry,index) in scope.row.skuList">
										<span :key="index" v-if="entry.name === item.name">{{entry.value}}</span>
									</template>
								</template>
							</el-table-column>
							<el-table-column label="库存数">
								<template slot-scope="scope">
									<el-input v-model="scope.row.stock"></el-input>
								</template>
							</el-table-column>
							<el-table-column label="销售价格">
								<template slot-scope="scope">
									<el-input v-model="scope.row.price"></el-input>
								</template>
							</el-table-column>
						</el-table>
					</div>
				</el-card>
			</el-form-item>
		</el-form>
		<div slot="footer" class="dialog-footer">
		  <el-button type="primary" @click="prev">上一步</el-button>
		  <el-button type="primary" @click="next">下一步</el-button>
		  <el-button type="primary" @click="query">查 看</el-button>
		</div>
	</div>
</template>

<script>
	export default {
		name: 'AttributeList',
		data() {
			const module = '/pms-category'
			return {
				url: {
					getAllCategorys: module + '/getAll'
				},
				form: {
					categoryId: [],
					spus: [],
					skus: [],
					stocks: [] 
				},
				categorys:[],
				rules: {
					categoryId: [
						{required: true, message: '请选择商品分类', trigger: 'blur'}
					]
				}
			}
		},
		created() {
			this.getAttribute()
		},
		methods: {
			query() {
				console.log(this.form.stocks)
			},
			next() {
				this.$refs['form'].validate(valid => {
					if(valid) {
						console.log(this.form.spus)
						for(let i = 0; i < this.form.spus.length ;i++) {
							if(!this.form.spus[i].value) {
								this.$message.error('必须输入所有属性值哦！')
								return
							}
						}
						for(let i = 0; i < this.form.skus.length ;i++) {
							if(this.form.skus[i].value.length === 0) {
								this.$message.error('请填写'+this.form.skus[i].name)
								return
							}
						}
						const pattern1 = /^[0-9]*$/ //匹配库存数
						const pattern2 = /^[0-9]+(\.[0-9]{1,2})?$/ //匹配价格
						for(let i = 0; i < this.form.stocks.length; i++) {
							if(!pattern1.test(this.form.stocks[i].stock)) {
								this.$message.error('库存数必须是正整数哦！')
								return
							}
							if(!pattern2.test(this.form.stocks[i].price)) {
								this.$message.error('价格必须是正的整数或两位小数哦！')
								return
							}
						}
						//console.log(this.form.spus)
						//将spus转换为字符串
						const spus = []
						const skus = []
						const stocks = []
						for(let i = 0; i < this.form.spus.length; i++) {
							spus.push(JSON.stringify(this.form.spus[i]))
						}
						for(let i = 0; i < this.form.skus.length; i++) {
							skus.push(JSON.stringify(this.form.skus[i]))
						}
						for(let i = 0; i < this.form.stocks.length; i++) {
							stocks.push(JSON.stringify(this.form.stocks[i]))
						}
						this.$emit('setForm', {categoryId:this.form.categoryId, spus: spus, skus: skus, stocks:stocks})
						this.$emit('next')
					}
				})
				
			},
			prev() {
				this.$emit('prev')
			},
			getAttribute() {
				this.get(this.url.getAllCategorys,{},response => {
					this.delChildren(response.categorys)
					this.categorys = response.categorys
				})
			},
			//删除级联选择器的叶子节点
			delChildren(items) {	
				for(let i=0; i<items.length; i++) {
					if(items[i].children.length === 0) {
						delete items[i].children
					} else {
						this.delChildren(items[i].children)
					}
				}
			},
			handleChange(categoryId) {
				//此处需要维护spu的name和value
				this.get('/pms-spu/getAttrs',{categoryIds: categoryId},response => {
					for(let i = 0; i < response.spus.length; i++) {
						this.form.spus.push({name: response.spus[i].name, value: ''})
					}
					//使用checkbox 需要给sku的属性加上一个value 值为一个数组
					for(let i = 0; i < response.skus.length; i++) {
						response.skus[i].value = []
						response.skus[i].addval = ' '
					}
					this.form.skus = response.skus
					console.log(response.skus)
				})
			},
			addItem(item) {
				item.inputList += '\n' + item.addval
			},
			delItem(item,index) {
				var list = item.inputList.split('\n')
				list.splice(index,index+1)
				let str = list[0]
				for(let i = 1;i < list.length; i++){
					str += '\n' + list[i]
				}
				item.inputList = str
			},
			//确认参数的选择
			checkAttr() {
				let arrayall = []
				const skuArr1 = this.form.skus[0]
				for(let i = 0;i < skuArr1.value.length; i++) {
					const arr = []
					arr.push({name: skuArr1.name, value: skuArr1.value[i]})
					arrayall.push(arr)
				}
				//console.log(arrayall)
				if(this.form.skus.length > 1) {
					const array = arrayall
					const skuArr2 = this.form.skus[1]
					arrayall = []
					for(let i = 0; i < array.length; i++) {
						for(let j = 0; j < skuArr2.value.length; j++) {
							const arr1 = []
							//添加name和value
							const length = array[i].length
							for(let k = 0; k < length; k++) {
								arr1.push(array[i][k])
							}
							// arr1.push(array[i])//只能以键值对的方式添加 不能直接添加一个{}
							arr1.push({name: skuArr2.name, value: skuArr2.value[j]})
							arrayall.push({skuList: arr1, stock:0,price:0})
						}
					}	
				}
				this.form.stocks = arrayall
			},
		}
	}
</script>

<style scoped lang="less">
	.el-input {
		width: auto;
		margin-right: 20px;
	}
</style>
