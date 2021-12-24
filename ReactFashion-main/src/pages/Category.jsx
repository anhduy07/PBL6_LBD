/* eslint-disable jsx-a11y/anchor-is-valid */
import React, { useEffect, useState } from 'react';
import Background from '../components/Background';
import ItemList from '../components/ItemList';
import SideBar from '../components/SideBar';
import Layout from '../layouts/Layout';
import goodsApi from '../api/goodsApi';

const Category = () => {
    const [goods, setGoods] = useState([]);
    const [query, setQuery] = useState({
        idCategory: 0,
        startPrice: -1,
        endPrice: 0,
    });

    const handleNameSearch = (name) => {
        if (name)
            goodsApi.getGoodsByName(name).then((res) => {
                console.log('res name: ', res);
                setGoods(res);
            });
        else
            goodsApi.getAllGoods().then((res) => {
                setGoods(res);
            });
    };

    const handleQueryChange = (queryValue) => {
        setQuery(queryValue);

        console.log('queryValue: ', queryValue);
    };

    useEffect(() => {
        goodsApi.getGoodsByQuery(query).then((res) => {
            setGoods(res);
        });
    }, [query]);

    return (
        <Layout>
            <Background />
            <div className='main-container shop-page left-sidebar'>
                <div className='container'>
                    <div className='row'>
                        <div className='main-content col-xl-9 col-lg-8 col-md-8 col-sm-12 has-sidebar'>
                            <div className=' auto-clear equal-container better-height akasha-products'>
                                <ul className='response-product product-list-grid row auto-clear equal-container better-height'>
                                    {goods.map((ele, index) => (
                                        <ItemList key={index} item={ele} />
                                    ))}
                                </ul>
                            </div>
                            {/* <div className='shop-control shop-after-control'>
                                <nav className='akasha-pagination'>
                                    <span className='page-numbers current'>
                                        1
                                    </span>
                                    <a className='page-numbers' href='#'>
                                        2
                                    </a>
                                    <a className='next page-numbers' href='#'>
                                        Next
                                    </a>
                                </nav>
                            </div> */}
                        </div>
                        <div className='sidebar col-xl-3 col-lg-4 col-md-4 col-sm-12'>
                            <SideBar
                                onNameSearch={handleNameSearch}
                                onQueryChange={handleQueryChange}
                            />
                        </div>
                    </div>
                </div>
            </div>
        </Layout>
    );
};

export default Category;
