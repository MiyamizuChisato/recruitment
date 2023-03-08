<script setup>
import Typography from '../../components/Typography.vue';
import { onMounted, ref, watch } from 'vue';
import { getReceivers } from '../../api/relation.js';
import { onApiSuccess } from '../../utils/assert.js';
import { go } from '../../utils/router.js';
import { useUserStore } from '../../store/userStore.js';

const userStore = useUserStore();
const page = ref({
    current: 1,
    records: []
});
const toChat = (id) => {
    go('/chat/' + id);
};
const pageReceivers = async () => {
    if (userStore.signed) {
        const { code, data } = await getReceivers(page.value.current);
        onApiSuccess(code, () => {
            page.value = data;
        });
    }
};
const getLast = (id) => {
    const chats = JSON.parse(localStorage.getItem('chat:' + userStore.userProfile.id + '-' + id));
    if (chats != null) {
        return chats[chats.length - 1].content;
    }
};
watch(() => page.value.current, () => {
    pageReceivers();
});
watch(() => userStore.signed, (value) => {
    if (value)
        pageReceivers();
});
onMounted(pageReceivers);
</script>
<template>
    <div p='x-10px' w='md:720px' margin-center>
        <typography color='primary'>
            <p text='20px'>聊天列表</p>
        </typography>
        <div text-regular text-center p='x-4 y-6' rounded='2' bg-container border shadow-box
             v-if='page.records.length===0'>
            <div flex-center><i text='20' i-material-symbols-sentiment-sad /></div>
            <div m='2'>还没有发起过聊天</div>
        </div>
        <div v-else p='x-4 y-6' rounded='2' bg-container border shadow-box>
            <div @click='toChat(i.receiver.id)' cursor='pointer' v-for='i in page.records' p='2' hover='bg-hover'
                 v-ripple flex-between-x bg-fill border>
                <div flex-center-x>
                    <v-avatar size='60' :image='i.receiver.avatar' />
                    <div>
                        <div p='x-4' text-strong>{{ i.receiver.nickname }}</div>
                        <div w='100%' p='x-4' text-secondary line-2>
                            {{ getLast(i.receiver.id) }}
                        </div>
                    </div>
                </div>
                <div w='24px' h='24px' rounded='full' text-light text-center>
                    <div v-if='i.unread>0' rounded='full' bg-danger>{{ i.unread }}</div>
                </div>
            </div>
            <v-pagination prev-icon='i-material-symbols-arrow-back-ios-new-rounded'
                          next-icon='i-material-symbols-arrow-forward-ios-rounded'
                          v-model='page.current' :total-visible='3'
                          :length='page.pages' text-strong />
        </div>
    </div>
</template>