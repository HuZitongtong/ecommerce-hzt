<template>
	<div>
		<el-form ref="myform" :model="form" label-width="80px" :rules="rules">
			<el-form-item label="属性名" prop="name">
				<el-input v-model="form.name"></el-input>
			</el-form-item>
		</el-form>
		<div slot="footer" class="dialog-footer">
		  <el-button @click="close">取 消</el-button>
		  <el-button type="primary" @click="save">确 定</el-button>
		</div>
	</div>	
</template>

<script>
	export default {
		name: 'SpuEdit',
		props: {
			id: {
				type: Number,
				default: null
			},
			categoryId: {
				type: Number,
				default: null
			}
		},
		created() {
			this.form.categoryId = this.categoryId
			if(this.id) {
				this.getone()
			} 
		},
		data() {
			const module = '/pms-spu'
			return {
				url: {
					add: module + '/add',
					update: module + '/update',
					getone: module + '/getone'
				},
				form: {
					name: '',
					categoryId: null
				},
				rules: {
					name: [
						{ required: true, message: '请输入属性名', trigger: 'blur' },
						{ min: 1, max: 20, message: '不能超过20个字符', trigger: 'blur' }
					],
				}
			}
		},
		methods: {
			save() {
				this.$refs['myform'].validate((flag) => {
					if(flag) {
						this.post(this.id ? this.url.update : this.url.add,this.form,() => {
							this.$emit('update:show', false)
							this.$emit('getTableData')
						}) 
					}
				})
			},
			getone(){
				this.get(this.url.getone,{id: this.id},response => {
					for(let key in this.form) {
						this.form[key] = response[key]
					}
					this.form.id = response.id
				})
			},
			//关闭页面
			close() {
				this.$emit('update:show', false)
			}
		}
	}
</script>

<style>
</style>
