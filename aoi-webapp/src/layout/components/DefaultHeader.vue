<script setup>
import DefaultHeaderMenu from './DefaultHeaderMenu.vue';
import DefaultHeaderPrefix from './DefaultHeaderPrefix.vue';
import DefaultHeaderSuffix from './suffix/DefaultHeaderSuffix.vue';
import ReturnTop from '../../components/ReturnTop.vue';
import { ref } from 'vue';
import DefaultTheme from './suffix/DefaultTheme.vue';
import { useUserStore } from '../../store/userStore.js';
import { useRoute } from 'vue-router';
import { go } from '../../utils/router.js';
import { useAppStore } from '../../store/appStore.js';

const userStore = useUserStore();
const route = useRoute();
const drawer = ref(false);
const appStore = useAppStore();
</script>
<template>
    <header class='animate__animated animate__fadeInDown' shadow='sm' flex-center>
        <default-header-prefix left='24' position='md:absolute' />
        <default-header-menu display='lt-md:!none' />
        <default-header-suffix right='24' display='lt-md:!none' position='absolute' />
        <div position='absolute' right='4'>
            <v-btn @click='drawer=true' color='transparent' icon flat>
                <i text-regular i-material-symbols-menu-rounded />
            </v-btn>
        </div>
    </header>
    <v-navigation-drawer v-model='drawer' bg-container temporary>
        <div p='2'>
            <default-theme />
        </div>
        <div m='y-10' text-strong>
            <div v-if='userStore.signed' flex-center>
                <v-avatar @click='go("/user")' size='100' :image='userStore.userProfile.avatar' />
            </div>
            <div v-else text='7' flex-center>CIALLO</div>
        </div>
        <div position='absolute' rounded='t-8' w='100%' bottom='0' text-regular bg-screen>
            <div p='y-6 x-20'>
                <div @click='go("/")' rounded='2' cursor='pointer' text='20px' m='y-2' p='2' hover='bg-hover'
                     flex-center>
                    <span :text='route.name==="home"?"primary":""'>主页</span>
                </div>
                <div @click='go("/position")' rounded='2' cursor='pointer' text='20px' m='y-2' p='2' hover='bg-hover'
                     flex-center>
                    <span :text='route.name==="position"?"primary":""'>职位</span>
                </div>
                <div @click='go("/relation")' rounded='2' cursor='pointer' text='20px' m='y-2' p='2' hover='bg-hover'
                     flex-center>
                    <span :text='route.name==="relation"?"primary":""'>消息</span>
                </div>
            </div>
            <div bg-container p='4'>
                <div v-if='userStore.signed'>
                    <v-btn m='y-2' v-if='userStore.userProfile.identity===3' block bg-warning flat>
                        <span text-light>管理</span>
                    </v-btn>
                    <v-btn @click='userStore.userLogout' block bg-danger flat>
                        <span text-light>退出</span>
                    </v-btn>
                </div>
                <v-btn @click='appStore.toggleSignDialog' block bg-primary flat v-else>
                    <span text-light>登陆</span>
                </v-btn>
            </div>
        </div>
    </v-navigation-drawer>
    <return-top />
</template>
<style scoped>
header {
    top: 0;
    z-index: 10;
    height: 54px;
    position: sticky;
    transition: top 555ms;
    background-color: var(--c-header);
    backdrop-filter: saturate(50%) blur(10px);
}
</style>
