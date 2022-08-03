<template>
	<div>
		<el-form ref="myform" :model="form" label-width="80px" :rules="rules">
			<el-form-item label="分类名称" prop="name">
				<el-input v-model="form.name"></el-input>
			</el-form-item>
			<!-- <el-form-item label="上级分类">
				<el-select v-if="this.id" v-model="form.parentId" filterable>
					<el-option></el-option>
				</el-select>
			</el-form-item> -->
		</el-form>
		<div slot="footer" class="dialog-footer">
		  <el-button @click="close">取 消</el-button>
		  <el-button type="primary" @click="save">确 定</el-button>
		</div>
	</div>
</template>

<script>
	export default {
		name: "CategoryEdit",
		props: {
			id: {
				type: Number,
				default: null
			},
			parentId: {
				type: Number,
				default: null
			},
			level: {
				type: Number,
				default: null
			}
		},
		created() {
			if(this.id) {
				this.getone()
			} else {
				this.form.level = this.level
				this.form.parentId = this.parentId
			}
			
		},
		data() {
			const module = '/pms-category'
			return {
				url: {
					add: module + '/add',
					getone: module + '/getone',
					update: module + '/update'
				},
				form: {
					name: '',
					parentId: null,
					level: null,
				},
				rules: {
					name: [
						{ required: true, message: '请输入分类名', trigger: 'blur' },
						{ min: 1, max: 20, message: '不能超过20个字符', trigger: 'blur' }
					]
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
