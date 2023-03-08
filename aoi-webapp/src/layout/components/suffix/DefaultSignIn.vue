<script setup>
import { useAppStore } from '../../../store/appStore.js';
import DefaultOtherSign from './DefaultOtherSign.vue';
import { reactive } from 'vue';
import { login } from '../../../api/userAuth.js';
import { onApiSuccess } from '../../../utils/assert.js';
import { useUserStore } from '../../../store/userStore.js';

defineProps({
    toggle: Function
});
const appStore = useAppStore();
const userStore = useUserStore();
const user = reactive({
    email: '',
    password: ''
});
const loginTrigger = async () => {
    const { code, data } = await login(user);
    onApiSuccess(code, () => {
        localStorage.setItem('token', data);
        userStore.token = data;
        appStore.toggleSignDialog();
    });
};
</script>
<template>
    <div class='sign-in' p='5' bg-container>
        <v-btn @click='appStore.toggleSignDialog' color='transparent' variant='flat' icon>
            <i text='10' i-heroicons-solid-arrow-left-circle text-info />
        </v-btn>
        <div text='24px' m='y-2' font='600' flex-between-x>
            <div text-strong>欢迎回来</div>
            <v-btn @click='toggle' variant='plain'>
                <span text-danger>注册</span>
            </v-btn>
        </div>
        <div m='b-4' text='18px' font='600' text-holder>
            请填写以下信息进行登录
        </div>
        <v-form text-regular>
            <div m='y-2'>
                <v-text-field
                    v-model='user.email'
                    prepend-icon='i-material-symbols-alternate-email'
                    label='邮箱' />
            </div>
            <div m='y-2'>
                <v-text-field
                    v-model='user.password'
                    prepend-icon='i-material-symbols-lock'
                    label='密码'
                    type='password' />
            </div>
            <div m='y-2'>
                <v-btn @click='loginTrigger' size='large' bg-primary block rounded flat>
                    <span text='18px' m='x-2' text-light>继续</span>
                    <i i-heroicons-solid-arrow-right-circle text-light />
                </v-btn>
            </div>
        </v-form>
        <default-other-sign />
    </div>
</template>
<style scoped>
@media screen and (min-width: 640px) {
    .sign-in {
        width: 500px;
        margin: auto;
        border-radius: 8px;
    }
}

@media screen and (max-width: 640px) {
    .sign-in {
        height: 100vh;
    }
}

.sign-in {
    overflow-y: auto;
}

.sign-in::-webkit-scrollbar {
    display: none;
}
</style>
