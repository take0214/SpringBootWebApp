import axios from "axios";

export default function DeleteTraining({ targetData, setPopUp }) {

    function deleteTraining(id) {

        return axios.get(`http://localhost:8080/delete-training?id=${id}`).then((response) => {
            console.dir(response.data)
            return response.data
        }).catch(error => {
            console.dir(error);
            window.alert('トレーニングの削除に失敗しました')
            return error
        });
    }

    return (
        <>
            <div id="content">
                <p>{targetData.name}のデータを削除します。よろしいですか？</p>
                <button onClick={async () => {
                        await deleteTraining(targetData.id)
                        setPopUp('off')
                }}>削除</button>
                <button onClick={() => setPopUp('off')} >キャンセル</button>
            </div>
        </>
    )
}