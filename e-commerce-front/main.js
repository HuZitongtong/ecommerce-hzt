import Vue from 'vue'
import './plugins/axios'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import uploadone from '@/components/upload/uploadone.vue'
import uploadmore from '@/components/upload/uploadmore.vue'
import myEditor from '@//components/editor/index.vue'

Vue.config.productionTip = false
Vue.prototype.img = (path) => {
	return 'https://hzt-images.oss-cn-beijing.aliyuncs.com/' + path
}
Vue.component('uploadone',uploadone)
Vue.component('uploadmore', uploadmore)
Vue.component('myEditor', myEditor)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
