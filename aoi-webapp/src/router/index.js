import { createRouter, createWebHistory } from 'vue-router';
import Default from '../layout/Default.vue';
import Home from '../view/home/Home.vue';
import Position from '../view/position/Position.vue';
import PositionDetail from '../view/position/PositionDetail.vue';
import Search from '../view/position/component/Search.vue';
import Relation from '../view/chat/Relation.vue';
import Chat from '../view/chat/Chat.vue';

const routes = [
    {
        path: '/',
        name: 'layout',
        component: Default,
        redirect: '/home',
        children: [
            {
                path: '/home',
                name: 'home',
                component: Home,
                meta: {
                    alive: true
                }
            },
            {
                path: '/position',
                name: 'position',
                component: Position,
                meta: {
                    alive: true
                }
            },
            {
                path: '/search/:key',
                name: 'search',
                component: Search
            },
            {
                path: '/detail/:id',
                name: 'detail',
                component: PositionDetail
            },
            {
                path: '/relation',
                name: 'relation',
                component: Relation
            },
            {
                path: '/chat/:id',
                name: 'chat',
                component: Chat
            },
            {
                name: 'user',
                path: '/user',
                component: () => import('../view/user/User.vue')
            },
        ]
    },
    {
        name: 'oauth',
        path: '/oauth/:type',
        component: () => import('../view/oauth/Oauth.vue'),
        children: []
    },
    {
        name: 'admin',
        path: '/admin',
        component: () => import('../layout/Admin.vue'),
        children: []
    }
];
const router = createRouter({
    routes,
    history: createWebHistory(),
    scrollBehavior(to, from, savedPosition) {
        if (savedPosition) {
            return savedPosition;
        } else {
            return { top: 0 };
        }
    }
});
export default router;
