import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify'
import router from '@/route'
import store from './store'
import axios from 'axios'
import VueAxios from 'vue-axios'
import DatePicker from 'vue2-datepicker'
import 'vue2-datepicker/index.css'


Vue.config.productionTip = false
Vue.use(VueAxios, axios)
Vue.use(DatePicker)


new Vue({
  router,
  vuetify,
  store,
  render: h => h(App)
}).$mount('#app')
