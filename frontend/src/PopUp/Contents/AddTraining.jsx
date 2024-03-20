import { useState } from "react"
import { useForm } from "react-hook-form"
import { BodyPart, changeJp } from "../../constant/BodyPart";
import axios from "axios";
import config from '../../config';

export default function AddTraining({ setPopUp }) {
    const [content, setContent] = useState('form');
    const [formData, setFormData] = useState();

    const {
        register,
        handleSubmit,
        formState: { errors },
    } = useForm();

    function postTraining(data) {

        const params = new URLSearchParams();
        params.append('name', data.name);
        params.append('bodyPart', data.part);

        return axios.post(`${config.API_URL}/training`, params,).then((response) => {
            console.dir(response.data)
            return response.data
        }).catch(error => {
            console.dir(error);
            window.alert('トレーニングの登録に失敗しました')
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
                <div id="content">
                    <p>トレーニング新規登録フォーム</p>
                    <form onSubmit={handleSubmit(onSubmit, onError)}>
                        <p>
                            <label htmlFor="name">名前</label>
                            <input {...register('name', { required: true })} type="text" name="name" />
                        </p>
                        <p>
                            <label htmlFor="part">部位</label>
                            <select {...register('part', { required: true })} >
                                <option value="">--部位を選択してください--</option>
                                <option value={BodyPart.CHEST}>{changeJp(BodyPart.CHEST)}</option>
                                <option value={BodyPart.SPINE}>{changeJp(BodyPart.SPINE)}</option>
                                <option value={BodyPart.LEG}>{changeJp(BodyPart.LEG)}</option>
                                <option value={BodyPart.SHOULDER}>{changeJp(BodyPart.SHOULDER)}</option>
                                <option value={BodyPart.ARM}>{changeJp(BodyPart.ARM)}</option>
                                <option value={BodyPart.ABS}>{changeJp(BodyPart.ABS)}</option>
                            </select>
                        </p>
                        <p>
                            <button type="submit">登録</button>
                            <button onClick={() => setPopUp('off')} >キャンセル</button>
                        </p>
                    </form>
                </div>
            </>
        )
    } else if (content === 'confirm') {
        return (
            <>
                <div id="content">
                    <p>以下の内容で登録してよろしいですか？</p>
                    <p>トレーニング名:{formData.name}, 部位:{changeJp(formData.part)}</p>
                    <button onClick={async () => {
                        await postTraining(formData)
                        setPopUp('off')
                    }}>登録</button>
                    <button onClick={() => setContent('form')}>キャンセル</button>
                </div >
            </>
        )
    }
}