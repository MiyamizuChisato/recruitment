<script setup>
import Typography from '../../components/Typography.vue';
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';
import { getPositionById } from '../../api/position.js';
import { onApiSuccess } from '../../utils/assert.js';
import { Viewer } from '@bytemd/vue-next';
import { postSchedule } from '../../api/schedule.js';
import { useToast } from 'vue-toastification';
import { useUserStore } from '../../store/userStore.js';
import { go } from '../../utils/router.js';
import { validateRelation } from '../../api/relation.js';

const route = useRoute();
const position = ref(null);
const toast = useToast();
const userStore = useUserStore();
const toChat = async () => {
    if (!userStore.signed) {
        toast.info('请先登陆');
        return;
    }
    if (userStore.userProfile.id !== position.value.user.id) {
        await validateRelation(userStore.userProfile.id, position.value.user.id);
        await go('/chat/' + position.value.user.id);
    } else {
        toast.warning('不能向自己发起聊天');
    }
};
const sendTrigger = async () => {
    if (!userStore.signed) {
        toast.info('请先登陆');
        return;
    }
    if (userStore.userProfile.resume === null || userStore.userProfile.resume === '') {
        toast.warning('请上传一份PDF格式的简历');
        return;
    }
    if (userStore.userProfile.id === position.value.user.id) {
        toast.warning('不可以将简历投递给自己');
        return;
    }
    const { code } = await postSchedule({
        positionId: position.value.id,
        publisher: position.value.user.id,
        asker: userStore.userProfile.id
    });
    onApiSuccess(code, () => {
        toast.success('简历投递成功');
    });
};
onMounted(async () => {
    const { code, data } = await getPositionById(route.params.id);
    onApiSuccess(code, () => {
        position.value = data;
    });
});
</script>
<template>
    <div v-if='position' p='x-2'>
        <div data-aos='animate__fadeInDown' class='info' h='200px' z='1' position='fixed' top='54px' left='0' right='0'
             border shadow-box>
            <div w='xl:1180px 100%' p='2' margin-center>
                <div text-warning>招聘中</div>
                <div text='28px' m='y-4' flex-between-x>
                    <div text-strong>{{ position.title }}</div>
                    <div text-danger>{{ position.wage }}</div>
                </div>
                <div text='14px' flex text-secondary>
                    <div flex-center-x>
                        <i text='20px' i-material-symbols-house-rounded />
                        <div m='l-1 r-2'>{{ position.company.name }}</div>
                    </div>
                    <div flex-center-x>
                        <i text='20px' i-material-symbols-location-on-rounded />
                        <div m='l-1 r-2'>{{ position.company.location }}</div>
                    </div>
                    <div flex-end flex-1 flex-center-x>
                        <i text='20px' i-heroicons-solid-lightning-bolt />
                        <div m='l-1 r-2'>{{ position.hot }}</div>
                    </div>
                </div>
                <div m='t-4' flex-between-x>
                    <div flex-center>
                        <v-avatar :image='position.user.avatar' />
                        <div text-regular m='x-2'>
                            {{ position.user.nickname }}
                        </div>
                    </div>
                    <div>
                        <v-btn @click='sendTrigger' m='r-2' bg-primary flat>
                            <span text-light>投递简历</span>
                        </v-btn>
                        <v-btn @click='toChat' m='r-2' bg-success flat>
                            <span text-light>聊一聊</span>
                        </v-btn>
                    </div>
                </div>
            </div>
        </div>
        <div p='t-200px'>
            <typography color='primary'>
                <p text='20px'>职位描述</p>
            </typography>
            <div rounded='2' p='4' border shadow-box bg-container>
                <Viewer text-regular :value='position.content' />
            </div>
            <typography color='primary'>
                <p text='20px'>公司简介</p>
            </typography>
            <div rounded='2' p='4' border shadow-box bg-container>
                <div flex-center-x>
                    <v-avatar size='64' :image='position.company.logo' rounded />
                    <span m='x-2' text='18px' text-strong>{{ position.company.name }}</span>
                </div>
                <p m='y-4' text-regular>
                    {{ position.company.description }}
                </p>
            </div>
        </div>
    </div>
</template>
<style scoped>
.info {
    background-color: var(--c-header);
    backdrop-filter: saturate(50%) blur(10px);
}
</style>