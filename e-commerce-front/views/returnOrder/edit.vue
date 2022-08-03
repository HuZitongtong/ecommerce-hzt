<template>
	<div>
		<el-form :model="form">
			<el-form-item label="原因类型" label-width="100px">
				<el-input v-model="form.type" style="width: auto;"></el-input>
			</el-form-item>
			<el-form-item label="是否启用" label-width="100px">
				<el-switch
				 :active-value="1"
				 :inactive-value="0"
				@change="handleNewStatusChange(form.active)"
				v-model="form.active">
				</el-switch>
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
		name : 'ReasonEdit',
		props: {
			id: {
				type: Number,
				default: null
			},
		},
		data() {
			const module = '/oms-return-reason'
			return {
				form: {
					type: '',
					active: 0
				},
				url: {
					add: module + '/add',
					update: module + '/update',
					getone: module + '/getone',
				}
			}
		},
		created() {
			if(this.id) {
				this.getone()
			}
		},
		methods: {
			//关闭页面
			close() {
				this.$emit('update:show', false)
			},
			handleNewStatusChange(active) {
				this.form.active = active
				console.log(this.form.active)
			},
			save() {
				this.post(this.id ? this.url.update : this.url.add,this.form,() => {
					this.$emit('update:show', false)
					this.$emit('getTableData')
				})
			},
			getone() {
				this.get(this.url.getone,{id:this.id},response => {
					this.form = response
				})
			}
			
		}
		
	}
</script>

<style>
</style>
