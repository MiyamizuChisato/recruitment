<script setup>
import { onMounted, ref, watch } from 'vue';
import { getMineSchedulePage } from '../../../api/schedule.js';
import { onApiSuccess } from '../../../utils/assert.js';
import { go } from '../../../utils/router.js';
import { validateRelation } from '../../../api/relation.js';
import { useUserStore } from '../../../store/userStore.js';

const userStore = useUserStore();
const toChat = async (id) => {
    await validateRelation(userStore.userProfile.id, id);
    await go('/chat/' + id);
};
const page = ref({
    current: 1
});
const toDetail = (id) => {
    window.open('/detail/' + id);
};
const getMine = async () => {
    const { code, data } = await getMineSchedulePage(page.value.current);
    onApiSuccess(code, () => {
        page.value = data;
    });
};
watch(() => page.value.current, async () => {
    await getMine();
});
onMounted(getMine);
</script>
<template>
    <div p='4' text-regular rounded='2' bg-container shadow-box border>
        <div m='y-4' v-for='i in page.records' flex-between-x>
            <div flex-center>
                <div @click='toDetail(i.position.id)' cursor='pointer' m='x-2' text='22px hover:primary'>
                    {{ i.position.title }}
                </div>
                <v-chip v-if='i.progress===2' color='success'>简历通过</v-chip>
                <v-chip v-else-if='i.progress===0' color='red'>简历淘汰</v-chip>
                <v-chip v-else color='info'>等待筛选</v-chip>
            </div>
            <div flex-center>
                <v-avatar
                    image='https://recruitment-1302869254.cos.ap-nanjing.myqcloud.com//63fec2670e6afa2f1cf09702.jpg' />
                <div p='x-2'>{{ i.user.nickname }}</div>
                <v-btn @click='toChat(i.user.id)' size='36' color='transparent' flat>
                    <i text-primary i-material-symbols-chat-rounded />
                </v-btn>
            </div>
        </div>
        <v-pagination prev-icon='i-material-symbols-arrow-back-ios-new-rounded'
                      next-icon='i-material-symbols-arrow-forward-ios-rounded'
                      v-model='page.current' :total-visible='3'
                      :length='page.pages' />
    </div>
</template>