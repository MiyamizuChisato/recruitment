<script setup>
import { useAppStore } from '../../../store/appStore.js';
import { onMounted, reactive, ref } from 'vue';
import { useDisplay } from 'vuetify';
import { getCompanies, postCompany } from '../../../api/company.js';
import { onApiSuccess } from '../../../utils/assert.js';
import { useToast } from 'vue-toastification';
import { Editor } from '@bytemd/vue-next';
import { postPosition } from '../../../api/position.js';
import { go } from '../../../utils/router.js';

const { mobile } = useDisplay();
const toast = useToast();
const appStore = useAppStore();
const dialog = ref(false);
const vo = reactive({
    name: '',
    description: '',
    location: ''
});
const position = reactive({
    title: '',
    content: '',
    wage: '',
    companyId: null
});
const logo = ref([]);
const appendix = ref([]);
const postCompanyTrigger = async () => {
    const fd = new FormData();
    fd.append('logo', logo.value[0]);
    fd.append('appendix', appendix.value[0]);
    fd.append('vo', new Blob([JSON.stringify(vo)], { type: 'application/json' }));
    const { code } = await postCompany(fd);
    onApiSuccess(code, () => {
        toast.success('新增企业成功，请等待审核');
        logo.value = [];
        appendix.value = [];
        vo.name = '';
        vo.description = '';
        dialog.value = false;
    });
};
const companies = ref([]);
const handleChange = (value) => {
    position.content = value;
};
const getCompanyList = async () => {
    const { data } = await getCompanies();
    companies.value = data;
};
const postPositionTrigger = async () => {
    const { code, data } = await postPosition(position);
    onApiSuccess(code, () => {
        go('/position/' + data);
    });
};
onMounted(() => {
    getCompanyList();
});
</script>
<template>
    <div rounded='2' border shadow-box text-regular bg-container>
        <div p='4' text='24px' flex-center>
            发布新职位
        </div>
        <div p='x-4 y-3'>
            <v-text-field v-model='position.title' label='职位名称' density='comfortable' hide-details />
        </div>
        <div p='x-4 y-3' flex-between-x>
            <v-select v-model='position.companyId' :theme='appStore.theme' :items='companies' item-title='name'
                      item-value='id' label='招聘企业' density='comfortable' hide-details>
                <template #append>
                    <v-btn @click='dialog=true' size='small' color='transparent' flat>
                        <span text-primary>新增</span>
                    </v-btn>
                </template>
            </v-select>
        </div>
        <div p='x-4 y-3'>
            <v-text-field v-model='position.wage' label='薪资水平' placeholder='例：15-20K/月 13薪'
                          density='comfortable' hide-details />
        </div>
        <div p='x-4 y-3'>
            <editor :value='position.content' @change='handleChange' />
        </div>
        <div flex-end p='x-4 y-3'>
            <v-btn @click='postPositionTrigger' bg-primary>
                <span text-light>发布职位</span>
            </v-btn>
        </div>
    </div>
    <v-dialog v-model='dialog' :fullscreen='mobile' transition='dialog-bottom-transition' persistent>
        <div class='dialog' border shadow-box text-regular bg-container>
            <div p='6' text='20px' text-center>新增企业</div>
            <div>
                <div p='4'>
                    <v-text-field v-model='vo.name' label='企业名称' />
                </div>
                <div p='4'>
                    <v-text-field v-model='vo.location' label='企业地址' />
                </div>
                <div p='4'>
                    <v-textarea v-model='vo.description' density='comfortable' hide-details label='企业描述' />
                </div>
                <div p='4'>
                    <v-file-input v-model='logo' accept='image/*' label='企业logo'
                                  prepend-icon='i-material-symbols-image'
                                  density='comfortable' hide-details />
                </div>
                <div p='4'>
                    <v-file-input v-model='appendix' label='认证附件'
                                  prepend-icon='i-material-symbols-attach-file-rounded'
                                  density='comfortable' hide-details />
                </div>
                <div flex-end>
                    <v-btn @click='postCompanyTrigger' m='4' bg-primary flat><span text-light>提交</span></v-btn>
                    <v-btn @click='dialog=false' m='4' bg-danger flat><span text-light>取消</span></v-btn>
                </div>
            </div>
        </div>
    </v-dialog>
</template>
<style scoped>
@media screen and (min-width: 640px) {
    .dialog {
        width: 500px;
        margin: auto;
        border-radius: 8px;
    }
}

@media screen and (max-width: 640px) {
    .dialog {
        height: 100vh;
    }
}

.dialog {
    overflow-y: auto;
}

.dialog::-webkit-scrollbar {
    display: none;
}
</style>