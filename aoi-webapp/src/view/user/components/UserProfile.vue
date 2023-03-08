<script setup>
import { useUserStore } from '../../../store/userStore.js';
import { ref } from 'vue';
import { putResume } from '../../../api/userProfile.js';
import { onApiSuccess } from '../../../utils/assert.js';

defineProps({
    modelValue: Boolean
});
const userStore = useUserStore();
const resumeRef = ref(null);
const resumeChangeHandler = async (e) => {
    const resume = e.target.files[0];
    const formData = new FormData();
    formData.append('resume', resume);
    const { code, data } = await putResume(formData);
    onApiSuccess(code, () => {
        userStore.user.resume = data;
    });
};
</script>
<template>
    <div rounded='2' border shadow-box text-regular bg-container>
        <div p='x-4 y-3' flex-between-x>
            <div>注册时间</div>
            <div>{{ userStore.userProfile.createTime }}</div>
        </div>
        <div p='x-4 y-3' flex-between-x>
            <div>身份标签</div>
            <div>{{ userStore.userProfile.identity }}</div>
        </div>
        <div p='x-4 y-3' flex-between-x>
            <div>个人简历</div>
            <input @change='resumeChangeHandler' accept='application/pdf' ref='resumeRef' type='file' hidden />
            <div v-if='userStore.userProfile.resume || modelValue'>
                <v-btn v-if='modelValue' @click='resumeRef.click()' color='transparent' size='small' flat>
                    <span text-primary>上传简历</span>
                </v-btn>
                <a target='_blank' :href='userStore.userProfile.resume' v-else>
                    查看简历
                </a>
            </div>
            <div text-holder v-else>
                无文件
            </div>
        </div>
    </div>
</template>
<style scoped>
a {
    text-decoration: none;
    color: var(--c-primary);
    font-size: 13px;
}
</style>