<script setup>
import DefaultSignIn from './DefaultSignIn.vue';
import DefaultSignUp from './DefaultSignUp.vue';
import { useAppStore } from '../../../store/appStore.js';
import { onMounted, onUnmounted, ref, watch } from 'vue';
import { useDisplay } from 'vuetify';
import { useUserStore } from '../../../store/userStore.js';
import { hasLength } from '../../../utils/string.js';
import { useToast } from 'vue-toastification';
import { go } from '../../../utils/router.js';
import { useRouter } from 'vue-router';

const { mobile } = useDisplay();
const appStore = useAppStore();
const userStore = useUserStore();
const router = useRouter();
const login = ref(true);
const toggle = () => {
    login.value = !login.value;
};
const storageHandler = (storage) => {
    if (storage.key && storage.key === 'token' && storage.newValue) {
        userStore.userLogin(storage.newValue);
        appStore.toggleSignDialog();
    }
};
const toast = useToast();
watch(() => userStore.token, (value) => {
    if (hasLength(value)) {
        userStore.getUserProfile();
    }
}, { immediate: true });
onMounted(() => {
    window.addEventListener('storage', storageHandler);
});
onUnmounted(() => {
    window.removeEventListener('storage', storageHandler);
});
</script>
<template>
    <div m='x-3'>
        <v-btn v-if='userStore.signed' size='small' color='transparent' icon flat>
            <v-avatar :image='userStore.userProfile.avatar' />
            <v-menu activator='parent' open-on-hover>
                <div rounded='md' shadow='md' p='2' m='y-2' bg-container text-strong border>
                    <div @click='go(`/user`)' class='menu-item' hover='bg-hover text-primary'>
                        <i text='18px' m='x-1' i-majesticons-user-line />
                        <span text='14px' flex='1' m='r-2' font='500'>我的账号</span>
                    </div>
                    <div @click='userStore.userLogout' class='menu-item' hover='bg-hover text-primary'>
                        <i text='18px' m='x-1' i-majesticons-door-exit-line />
                        <span text='14px' flex='1' m='r-2' font='500'>退出登录</span>
                    </div>
                </div>
            </v-menu>
        </v-btn>
        <v-btn @click='appStore.toggleSignDialog' v-else bg-primary flat>
            <span text-light>登陆</span>
        </v-btn>
    </div>
    <v-row justify='center'>
        <v-dialog v-model='appStore.signDialog' transition='dialog-bottom-transition'
                  :fullscreen='mobile' persistent>
            <default-sign-in :toggle='toggle' v-if='login' />
            <default-sign-up :toggle='toggle' v-else />
        </v-dialog>
    </v-row>
</template>
<style scoped>
.menu-item {
    cursor: pointer;
    border-radius: 6px;
    padding: 4px 2px;
    display: flex;
    align-items: center;
    margin-top: 2px;
    margin-bottom: 2px;
    transition: background-color, color 333ms;
}
</style>