"use strict";

import Vue from 'vue';
import axios from "axios";
import { Loading,Notification } from 'element-ui';
import qs from 'qs'
// Full config:  https://github.com/axios/axios#request-config
// axios.defaults.baseURL = process.env.baseURL || process.env.apiUrl || '';
// axios.defaults.headers.common['Authorization'] = AUTH_TOKEN;
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

let config = {
	baseURL: 'http://localhost:8081'
  // baseURL: process.env.baseURL || process.env.apiUrl || ""
  // timeout: 60 * 1000, // Timeout
  // withCredentials: true, // Check cross-site Access-Control
};

const _axios = axios.create(config);//创建了一个axios对象
//过滤器
_axios.interceptors.request.use(//请求（前）过滤器
  function(config) {
    // Do something before request is sent
	config.paramsSerializer = (params) => {
		return qs.stringify(params, {arrayFormat: 'repeat'})
	}
    return config;
  },
  function(error) {
    // Do something with request error
    return Promise.reject(error);
  }
);

// Add a response interceptor
_axios.interceptors.response.use(//响应过滤器
  function(response) {
    // Do something with response data
    return response;
  },
  function(error) {
    // Do something with response error
    return Promise.reject(error);
  }
);


//自定义vue属性
//可用this.name来接收
//使用变量来完成复用调用，优化接口
const request = (url,method,params,callback) => {
	//callback是传一个函数，即为我想让request干什么
	//get和post请求体内容不同 需要做区分
	const myLoading = Loading.service({
		text: "拼命加载中",
		background: 'rgba(0,0,0,0.4)'
	});
	const myConfig = {
		url: url,
		method: method,
	}
	if (method === 'get') {
		myConfig.params = params
	} else {
		//如果是post的情况
		const formData = new FormData()
		for (let key in params) {
			if(params[key] instanceof Array) {
				for(let i = 0; i < params[key].length; i++) {
					formData.append(key, params[key][i])
				}
			}else {
				formData.append(key, params[key])
			}		
		}
		myConfig.data = formData
	}
	_axios.request(myConfig).then(response => {
		if (response.data.code === 200) {
			callback(response.data)//把参数传出去
			//this.tableData = response.data.obj
		} else {
			Notification.error({
				title: '标题',
				message: response.data.message
			})
		}		
	}).catch(error => {
		//如果出错后
		Notification.error({
			title: 'error',
			message: '前后端通信出错'
		})
	}).finally(() => {
		//无论发生什么 最后都会干什么
		//关闭加载条
		myLoading.close()
	})
}
Vue.prototype.axios = _axios 
Vue.prototype.request = request
Vue.prototype.get = (url,params,callback) => {
	request(url,'get',params,response => {
		callback(response.obj)
	})
}
Vue.prototype.post = (url,params,callback) => {
	request(url,'post',params,response => {
		//增删改需要提示信息
		Notification.success({
			title:'成功',
			message: response.message
		})
		callback(response.obj)
	})
}


// Plugin.install = function(Vue, options) {
//   Vue.axios = _axios;
//   window.axios = _axios;
//   Object.defineProperties(Vue.prototype, {
//     axios: {
//       get() {
//         return _axios;
//       }
//     },
//     $axios: {
//       get() {
//         return _axios;
//       }
//     },
//   });
// };

// Vue.use(Plugin)

// export default Plugin;
