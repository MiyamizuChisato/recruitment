<script setup>
import { useAppStore } from '../../../store/appStore.js';
import { reactive } from 'vue';
import { sendVerifyMail } from '../../../api/mail.js';
import { onApiSuccess } from '../../../utils/assert.js';
import { useToast } from 'vue-toastification';
import { register } from '../../../api/userAuth.js';
import { useUserStore } from '../../../store/userStore.js';

defineProps({
    toggle: Function
});
const appStore = useAppStore();
const userStore = useUserStore();
const toast = useToast();
const user = reactive({
    email: '',
    code: '',
    password: '',
    sure: '',
    nickname: ''
});
const sendVerifyMailTrigger = async () => {
    const { code, data } = await sendVerifyMail(user.email);
    onApiSuccess(code, () => {
        toast.success(data);
    });
};
const registerTrigger = async () => {
    if (user.password === user.sure) {
        const { code, data } = await register(user);
        onApiSuccess(code, () => {
            localStorage.setItem('token', data);
            userStore.token = data;
            toast.success('注册成功，已自动登陆');
            appStore.toggleSignDialog();
        });
    } else {
        toast.error('两次密码不一致');
    }
};
</script>
<template>
    <div class='sign-up' p='5' bg-container>
        <v-btn @click='appStore.toggleSignDialog' color='transparent' variant='flat' icon>
            <i text='10' i-heroicons-solid-arrow-left-circle text-info />
        </v-btn>
        <div text='24px' m='y-2' font='600' flex-between-x>
            <div text-strong>欢迎加入</div>
            <v-btn @click='toggle' variant='plain'>
                <span text-danger>登录</span>
            </v-btn>
        </div>
        <div m='b-4' text='18px' font='600' text-holder>
            请填写以下信息进行注册
        </div>
        <v-form text-regular>
            <div m='y-1'>
                <v-text-field
                    v-model='user.email'
                    label='邮箱'
                    prepend-icon='i-material-symbols-alternate-email'>
                    <template #append-inner>
                        <v-btn @click='sendVerifyMailTrigger' size='small' bg-success flat>
                            <span text-light>验证</span>
                        </v-btn>
                    </template>
                </v-text-field>
            </div>
            <div m='y-1'>
                <v-text-field
                    label='验证码'
                    v-model='user.code'
                    prepend-icon='i-material-symbols-check-box' />
            </div>
            <div m='y-1'>
                <v-text-field
                    v-model='user.password'
                    label='密码'
                    type='password'
                    prepend-icon='i-material-symbols-lock' />
            </div>
            <div m='y-1'>
                <v-text-field
                    v-model='user.sure'
                    label='确认密码'
                    type='password'
                    prepend-icon='i-material-symbols-lock' />
            </div>
            <div m='y-1'>
                <v-text-field
                    v-model='user.nickname'
                    label='昵称'
                    prepend-icon='i-fluent-rename-28-filled' />
            </div>
            <div m='y-1'>
                <v-btn @click='registerTrigger' size='large' bg-primary block rounded flat>
                    <span text='18px' m='x-2' text-light>继续</span>
                    <i i-heroicons-solid-arrow-right-circle text-light />
                </v-btn>
            </div>
        </v-form>
    </div>
</template>
<style scoped>
@media screen and (min-width: 640px) {
    .sign-up {
        width: 500px;
        margin: auto;
        border-radius: 8px;
    }
}

@media screen and (max-width: 640px) {
    .sign-up {
        height: 100vh;
    }
}

.sign-up {
    overflow-y: scroll;
}

.sign-up::-webkit-scrollbar {
    display: none;
}

:deep(.v-input__append) {
    padding: 0;
    align-items: center;
}

:deep(.v-field__append-inner) {
    padding: 0;
    align-items: center;
}
</style>
