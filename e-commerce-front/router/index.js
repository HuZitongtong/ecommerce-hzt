import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [
  {
	path: '/login',
	name: 'Login',
	component: () => import('@/views/login')
  },
  {
    path: '/',
    name: 'Index',
    component: () => import('@/views'),
	children: [
		{
		  path: '/user',
		  name: 'UserList',
		  component: () => import('@/views/user')
		},
		{
		  path: '/home',
		  name: 'Home',
		  component: () => import('@/views/home')
		},
		{
		  path: '/role',
		  name: 'RoleList',
		  component: () => import('@/views/role')
		},
		{
		  path: '/roleusers',
		  name: 'RoleUsers',
		  component: () => import('@/views/role/users')
		},
		{
		  path: '/roleResource',
		  name: '/RoleResource',
		  component: () => import('@/views/role/roleResource')
		},
		{
		  path: '/category',
		  name: 'CategoryList',
		  component: () => import('@/views/category')
		},
		{
		  path: '/spu',
		  name: 'SpuList',
		  component: () => import('@/views/spu')
		},
		{
		  path: '/sku',
		  name: 'SkuList',
		  component: () => import('@/views/sku')
		},
		{
		  path: '/product',
		  name: 'ProductList',
		  component: () => import('@/views/product')
		},
		{
		  path: '/productAdd',
		  name: 'ProductAdd',
		  component: () => import('@/views/product/add')
		},
		{
		  path: '/order',
		  name: 'OrderList',
		  component: () => import('@/views/order')
		},
		{
		  path: '/orderDetail',
		  name: 'OrderDetail',
		  component: () => import('@/views/order/orderDetail')
		},
		{
		  path: '/setting',
		  name: 'OrderSetting',
		  component: () => import('@/views/order/setting')
		},
		{
		  path: '/deliverOrderList',
		  name: 'deliverOrderList',
		  component: () => import('@/views/order/deliverOrderList')
		},
		{
		  path: '/returnOrder',
		  name: 'ReturnOrder',
		  component: () => import('@/views/returnOrder')
		},
		{
		  path: '/applyDetail',
		  name: 'ApplyDetail',
		  component: () => import('@/views/returnOrder/detail')
		},
		{
		  path: '/returnReason',
		  name: 'ReturnReason',
		  component: () => import('@/views/returnOrder/returnReason')
		},
		{
		  path: '/recommend',
		  name: 'SubjectRecommend',
		  component: () => import('@/views/recommend')
		},
		{
		  path: '/advertise',
		  name: 'advertiseList',
		  component: () => import('@/views/advertise')
		},
		{
		  path: '/advertiseEdit',
		  name: 'advertiseEdit',
		  component: () => import('@/views/advertise/edit')
		}
	]
  },
 
  
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
