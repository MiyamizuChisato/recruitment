import http from '../utils/http.js';

const BASE_PATH = '/schedule';
export const postSchedule = (data) => {
    return http.post(`${BASE_PATH}/post/schedule`, data);
};
export const getSchedulePage = (positionId, current) => {
    return http.get(`${BASE_PATH}/get/page/${positionId}/${current}`);
};
export const putProgress = (scheduleId, progress) => {
    return http.put(`${BASE_PATH}/put/${scheduleId}/${progress}`);
};
export const getMineSchedulePage = (current) => {
    return http.get(`${BASE_PATH}/get/mine/page/${current}`);
};