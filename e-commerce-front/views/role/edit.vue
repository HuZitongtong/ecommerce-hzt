<template>
	<div>
		
		<el-form ref="myform" :rules="rules" :model="form">
			<el-form-item label="角色名" label-width="100px" prop="name">
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
		name: 'RoleEdit',
		props: {
			id: {
				type: Number,
				default: null
			}
		},
		created() {
			//获取父组件传过来的数据 此方法为动态接收 this.$ attrs
			//可使用props来静态接收
			//动态和静态只能使用一种
			if (this.id) {
				this.getone()
			}
		},
		data() {
			const module = '/ums_role'
			return {
				url: {
					add: module + '/add',
					getone: module + '/getone',
					update: module + '/update',
				},
				form: {
					name: ''
				},
				rules: {
					name: [
						{ required: true, message: '请输入角色名', trigger: 'blur' },
						{ min: 1, max: 10, message: '不能超过10个字符', trigger: 'blur' }
					]
				}
			}
		},
		methods: {
			//保存修改/保存新建用户
			save() {
				this.$refs['myform'].validate((flag) => {
					if(flag) {
						this.post(this.id ? this.url.update : this.url.add,this.form,response => {
							this.$emit('update:show', false)
							this.$emit('getTableData')
						}) 
					}
				})
				
			},
			//通过id查到该用户的数据 填写表单
			getone() {
				this.get(this.url.getone,{id: this.id},response => {
					for (let key in this.form) {
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
