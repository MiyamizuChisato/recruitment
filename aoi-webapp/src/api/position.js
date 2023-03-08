import http from '../utils/http.js';

const BASE_PATH = '/position';
export const postPosition = (data) => {
    return http.post(`${BASE_PATH}/post/position`, data);
};
export const getManagePositions = () => {
    return http.get(`${BASE_PATH}/get/manage/positions`);
};
export const getCardPage = (current) => {
    return http.get(`${BASE_PATH}/get/page/${current}`);
};
export const getPositionById = (id) => {
    return http.get(`${BASE_PATH}/get/${id}`);
};
export const getCarousel = () => {
    return http.get(`${BASE_PATH}/get/carousel`);
};
export const pageSearch = (keyword, current) => {
    return http.get(`${BASE_PATH}/get/${keyword}/${current}`);
};
export const deletePosition = (id) => {
    return http.delete(`${BASE_PATH}/delete/${id}`);
};