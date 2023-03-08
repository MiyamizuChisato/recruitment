<script setup>
import { useUserStore } from '../../../store/userStore.js';
import { ref } from 'vue';
import { putAvatar, putProfile } from '../../../api/userProfile.js';
import { onApiSuccess } from '../../../utils/assert.js';
import { useToast } from 'vue-toastification';
import Typography from '../../../components/Typography.vue';

defineProps({
    modelValue: Boolean
});
const emits = defineEmits();
const userStore = useUserStore();
let toast = useToast();
const avatarRef = ref(null);
const avatarChangeHandler = async (e) => {
    const avatar = e.target.files[0];
    const formData = new FormData();
    formData.append('avatar', avatar);
    const { code, data } = await putAvatar(formData);
    onApiSuccess(code, () => {
        userStore.user.avatar = data;
    });
};
const putProfileTrigger = async () => {
    emits('update:modelValue', false);
    const { code } = await putProfile(userStore.userProfile);
    onApiSuccess(code, () => {
        toast.success('个人信息已更新');
    });
};
</script>
<template>
    <div data-aos='animate__fadeInDown'>
        <typography color='primary'>
            <div text='20px'>用户信息</div>
        </typography>
        <div p='x-6 y-3' rounded='2' v-if='modelValue' border bg-container shadow-box>
            <div h='30px' text-end>
                <v-btn @click='putProfileTrigger' size='small' variant='text'>
                <span text-primary>
                    保存资料
                </span>
                </v-btn>
            </div>
            <div flex-center>
                <div @click='avatarRef.click' p='10px' rounded='full' bg-hover>
                    <v-avatar size='100' :image='userStore.userProfile.avatar' />
                    <input @change='avatarChangeHandler' accept='image/*' ref='avatarRef' type='file' hidden />
                </div>
            </div>
            <div w='200px' m='4' text='20px' text-regular margin-center>
                <v-text-field v-model='userStore.user.nickname' placeholder='你的用户名' label='用户名'
                              rounded='!2' variant='outlined' density='comfortable' hide-details />
            </div>
            <div w='300px' m='4' text='20px' text-regular margin-center>
                <v-text-field v-model='userStore.user.email' placeholder='联系邮件，用于接受系统消息' label='邮箱'
                              rounded='!2' variant='outlined' density='comfortable' hide-details />
            </div>
            <div m='4' text='18px' text-secondary text-center>
                <v-textarea v-model='userStore.user.motto' placeholder='你的个性简介' variant='outlined'
                            density='comfortable' label='个性签名' hide-details />
            </div>
        </div>
        <div p='x-6 y-3' rounded='2' v-else border bg-container shadow-box>
            <div h='30px' text-end>
                <v-btn @click='emits("update:modelValue",true)' size='small' variant='text'>
                <span text-primary>
                    修改资料
                </span>
                </v-btn>
            </div>
            <div flex-center>
                <v-avatar size='110' :image='userStore.userProfile.avatar' />
            </div>
            <div m='2' text='20px' text-regular text-center>
                {{ userStore.userProfile.nickname }}
            </div>
            <div m='4' text='18px' text-secondary text-center>
                {{ userStore.userProfile.motto || '这个人很懒，什么也没写' }}
            </div>
        </div>

    </div>
</template>
