import styles from '../PopUp.module.css'

import { useState } from "react"
import { useForm } from "react-hook-form"
import axios from "axios";
import config from '../../config';

export default function AddRecord({ targetData, setPopUp }) {
    const [content, setContent] = useState('form');
    const [formData, setFormData] = useState();
    const [selectedDate, setSelectedDate] = useState(formatDate(new Date()));

    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm({ defaultValues: { date: new Date(), weight: targetData.maxweight, reps: 8 } });

    function formatDate(dt) {
        var y = dt.getFullYear();
        var m = ('00' + (dt.getMonth() + 1)).slice(-2);
        var d = ('00' + dt.getDate()).slice(-2);
        return (y + '-' + m + '-' + d);
    }



    function postRecord({formData, targetData}) {
        const params = new URLSearchParams();
        params.append('date', formData.date.getTime());
        params.append('weight', formData.weight);
        params.append('reps', formData.reps);
        params.append('trainingId', targetData.id);

        return axios.post(`${config.API_URL}/record`, params,).then((response) => {
            console.dir(response.data)
            return response.data
        }).catch(error => {
            console.dir(error);
            window.alert('トレーニング記録の登録に失敗しました')
            return error
        });
    }

    function onSubmit(data) {

        console.log(data)

        setFormData(data)
        setContent('confirm')
    }

    function onError(data) {
        window.alert('入力が不正です')
    }

    if (content === 'form') {

        return (
            <>
                <div className={styles.content}>
                    <p>{targetData.name}のワークアウトを登録</p>
                    <form onSubmit={handleSubmit(onSubmit, onError)}>

                        <p>
                            <label htmlFor='date'>日付</label>
                            <input type="date" value={selectedDate}
                                {...register('date', { required: true, valueAsDate: true })}
                                name="date" onChange={(date) => setSelectedDate(date.target.value)}></input>
                        </p>
                        <p>
                            <label htmlFor='weight'>重量</label>
                            <input type="number"
                                {...register('weight', { required: true })}
                                name="weight"></input>
                        </p>
                        <p>
                            <label htmlFor='reps'>レップ数</label>
                            <input type="number"
                                {...register('reps', { required: true })}
                                name="reps"></input>
                        </p>
                        <p>
                            <button type="submit">登録</button>
                            <button onClick={() => setPopUp('off')}>キャンセル</button>
                        </p>
                    </form>
                </div >
            </>
        )


    } else if (content === 'confirm') {
        return (
            <>
                <div className={styles.content}>
                    <p>以下の内容で登録してよろしいですか？</p>
                    <p>日付：{formatDate(formData.date)}、重量：{formData.weight}、レップ数：{formData.reps}</p>
                    <button onClick={async() => {
                        await postRecord({formData, targetData})
                        setPopUp('off')
                    }}>登録</button>
                    <button onClick={() => setContent('form')}>キャンセル</button>
                </div>
            </>
        )
    }
}