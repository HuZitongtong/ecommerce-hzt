<template>
	<div>
		<el-form ref="form" :model="form" :rules="rules" label-width="100px">
			<el-form-item label="商品名称" prop="name">
				<el-input v-model="form.name" placeholder="请输入商品名称" class="input-width"></el-input>
			</el-form-item>
			<el-form-item label="副标题" prop="subTitle">
				<el-input v-model="form.subTitle" placeholder="请输入副标题" class="input-width"></el-input>
			</el-form-item>
			<el-form-item label="商品售价" prop="price">
				<el-input v-model="form.price" placeholder="请输入商品名称" class="input-width"></el-input>
			</el-form-item>
			<el-form-item label="关键词" prop="keywords">
				<el-input v-model="form.keywords" placeholder="请输入关键词" class="input-width"></el-input>
			</el-form-item>
		</el-form>
		<div slot="footer" class="dialog-footer">
		  <el-button type="primary" @click="next">下一步</el-button>
		</div>
		
		<!-- <template v-if="add === 1">
			<el-button type="primary" size="small" @click="next">下一步</el-button>
		</template>
		<template v-else>
			<el-button type="primary" size="small" @click="update">保存</el-button>
		</template> -->
	</div>
</template>

<script>
	export default {
		name: 'BaseForm',
		props: {
			params: {
				type: Object,
				default: () => { return {}}
			},
			id: {
				type: Number,
				default: null
			},
			add: {
				type: Number,
				default: null
			}
		},
		data() {
			return {
				form: {
					name: '',
					subTitle: '',
					price: '',
					keywords: ''
				},
				rules: {
					name: [
						{required: true,message: '请输入商品名称', trigger: 'blur'}
					],
					subTitle: [{required: true,message: '请输入副标题', trigger: 'blur'}],
					price: [
						{required: true,message: '请输入标准价格', trigger: 'blur'},
						{pattern: /^[0-9]+(\.[0-9]{1,2})?$/, message: '请输入正确的价格', trigger: 'blur'}
					],
				}
			}
		},
		methods: {
			next() {
				this.$refs['form'].validate(valid => {
					if(valid) {
						this.$emit('setForm',this.form)
						this.$emit('next') 
					}
				})
			},
			
		}
	}
</script>

<style lang="less">
	.input-width {
		width: 250px;
		margin-right: 15px;
	}
</style>
