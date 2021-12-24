import axiosClient from './axiosClient';

const API_KEY = '/goods';

const goodsApi = {
    getAllGoods: () => {
        return axiosClient.get(`${API_KEY}/getAll`);
    },

    getAllGoodsSaleOff: () => {
        return axiosClient.get(`${API_KEY}/getSaleOff`);
    },

    getAllGoodsHotTrend: () => {
        return axiosClient.get(`${API_KEY}/getHotTrend`);
    },

    getGoodsById: (goodId) => {
        return axiosClient.get(`${API_KEY}/findById/${goodId}`);
    },

    getGoodsByName: (name) => {
        return axiosClient.get(`${API_KEY}/inputSearch/${name}`);
    },

    getGoodsByQuery: (query) => {
        return axiosClient.get(`${API_KEY}/search`, {
            params: {
                ...query,
            },
        });
    },

    getAllCategories: () => {
        return axiosClient.get(`${API_KEY}/getAllTypeGoods`);
    },
};

export default goodsApi;
