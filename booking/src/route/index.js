import Vue from 'vue'
import Router from 'vue-router'
import Signin from '../components/Signin'
import Idx from '../components/Idx'
import Calendar from '../components/Calendar'
import LogOut from '../components/LogOut'

Vue.use(Router)

const router = new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
        { path: '/', component: Idx },
        { path: '/signin', component: Signin },
        { path: '/booking', component: Calendar },
        { path: '/logout', component: LogOut },
    ],
})


export default router
