import http from '../utils/http.js';

const BASE_PATH = '/chat';
export const getUnreadMessage = (sender) => {
    return http.get(`${BASE_PATH}/get/unread/${sender}`);
};