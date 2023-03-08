<script setup>
import { onMounted, ref, watch } from 'vue';
import { deletePosition, getManagePositions } from '../../../api/position.js';
import { onApiSuccess } from '../../../utils/assert.js';
import { getSchedulePage, putProgress } from '../../../api/schedule.js';

const positions = ref([]);
const manageFlag = ref(false);
const page = ref({
    current: 1,
    records: []
});
const tempId = ref(0);
const getManage = async () => {
    const { code, data } = await getManagePositions();
    onApiSuccess(code, () => {
        positions.value = data;
    });
};
const removePosition = async (id) => {
    if (confirm('确认删除这个职位吗？')) {
        await deletePosition(id);
        await getManage();
    }
};
const toDetail = (id) => {
    window.open('/detail/' + id);
};
const checkResume = (url) => {
    window.open(url);
};
const manage = (positionId) => {
    manageFlag.value = true;
    tempId.value = positionId;
    getPage();
};
const putSchedule = async (scheduleId, progress) => {
    const { code } = await putProgress(scheduleId, progress);
    onApiSuccess(code, () => {
        page.value.current = 1;
        getPage();
    });
};
const getPage = async () => {
    const { code, data } = await getSchedulePage(tempId.value, page.value.current);
    onApiSuccess(code, () => {
        page.value = data;
    });
};
watch(() => page.value.current, () => {
    getPage();
});
onMounted(getManage);
</script>
<template>
    <div rounded='2' border shadow-box text-regular bg-container>
        <div p='4' flex-y flex-center v-if='positions.length===0'>
            <i text='40' i-material-symbols-sentiment-sad />
            <div>没有可以管理的职位</div>
        </div>
        <div p='4' flex-between-x v-for='position in positions' :key='position.id' v-else>
            <div flex-1>
                <span text='18px' text-strong>{{ position.title }}</span>
                <span> - </span>
                <span text-secondary>{{ position.company }}</span>
            </div>
            <div>
                <v-btn @click='toDetail(position.id)' size='small' color='transparent' flat>
                    <span text-primary>查看详情</span>
                </v-btn>
                <v-btn @click='manage(position.id)' size='small' color='transparent' flat>
                    <span text-warning>甄选简历</span>
                </v-btn>
                <v-btn @click='removePosition(position.id)' size='small' color='transparent' flat>
                    <span text-danger>删除职位</span>
                </v-btn>
            </div>
        </div>
    </div>
    <v-dialog v-model='manageFlag' width='auto'>
        <div p='4' rounded='2' border shadow-box bg-container text-regular>
            <div text='20px'>简历甄选</div>
            <template v-if='page.records.length<=0'>
                <div p='10'>
                    <i text='40' i-material-symbols-sentiment-sad />
                    <p>暂时没有人投递简历。</p>
                </div>
            </template>
            <template v-else>
                <div p='4' v-for='i in page.records' flex-between-x>
                    <div flex-center-x>
                        <v-avatar :image='i.user.avatar' />
                        <span m='x-2'> {{ i.user.nickname }}</span>
                    </div>
                    <div m='x-2' flex-center-x>
                        <v-btn @click='checkResume(i.user.resume)' size='small' color='transparent' flat>
                            <span text-primary>查看简历</span>
                        </v-btn>
                        <v-btn @click='putSchedule(i.id,2)' size='small' color='transparent' flat>
                            <span text-success>简历合格</span>
                        </v-btn>
                        <v-btn @click='putSchedule(i.id,0)' size='small' color='transparent' flat>
                            <span text-danger>简历淘汰</span>
                        </v-btn>
                    </div>
                </div>
                <v-pagination prev-icon='i-material-symbols-arrow-back-ios-new-rounded'
                              next-icon='i-material-symbols-arrow-forward-ios-rounded'
                              v-model='page.current' :total-visible='3'
                              :length='page.pages' />
            </template>
        </div>
    </v-dialog>
</template>