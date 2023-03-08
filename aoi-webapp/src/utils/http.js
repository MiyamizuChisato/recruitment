import axios from 'axios';
import { useToast } from 'vue-toastification';

const http = axios.create({
    baseURL: '/aoi',
    timeout: 10000,
    withCredentials: true
});
const beforeRequest = config => {
    const token = localStorage.getItem('token');
    token && (config.headers.Authorization = token);
    return config;
};
const responseSuccess = response => {
    const toast = useToast();
    const apiResult = response.data;
    if (apiResult.code < 0) {
        toast.error(apiResult.message || '服务器君发生错误了 ๐•ᴗ•๐ ');
    }
    return Promise.resolve(response.data);
};
const responseFailed = error => {
    const toast = useToast();
    toast.error('与服务器の羁绊断开了 •﹏• ');
    return Promise.reject(error);
};
http.interceptors.request.use(beforeRequest);
http.interceptors.response.use(responseSuccess, responseFailed);
export default http;