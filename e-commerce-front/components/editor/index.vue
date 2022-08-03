<template>
	<VueEditor useCustomImageHandler @image-added="handleImageAdded" v-model="content"></VueEditor>
</template>

<script>
import { VueEditor } from "vue2-editor";
export default {
	name: 'Editor',
	props: {
		value: {
			type:String,
			default: ''
		}
	},
	created() {
		this.content = this.value  
	},
	watch: {
		content: {
			handler(newval) {
				this.$emit('input',newval)
			}
		}
	},
	components: {
		VueEditor 
	},
	data() {
		return {
			content: ''
		}
	},
	methods: {
		handleImageAdded(file, Editor, cursorLocation, resetUploader) {
			// console.log(file)
			// console.log(Editor)
			const formData = new FormData() 
			formData.append('file',file)
			this.axios.request('/img/upload',{
				method: 'post',
				data: formData
			}).then(response => {
				console.log(response)
				Editor.insertEmbed(cursorLocation, 'image', this.img(response.data))
				resetUploader()
			})
		},
	}
}
</script>

<style scoped lang="less ">
</style>
