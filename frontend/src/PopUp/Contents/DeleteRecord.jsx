import axios from "axios";

export default function DeleteRecord({ targetData, targetRecord, setPopUp }) {

    function deleteRecord(id) {

        return axios.get(`http://localhost:8080/delete-record?id=${id}`).then((response) => {
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
                <p>{targetRecord.date}の{targetData.name}の記録を削除します。よろしいですか？</p>
                <button onClick={async() => {
                    await deleteRecord(targetRecord.id)
                    setPopUp('off')
                }}>削除</button>
                <button onClick={() => setPopUp('off')} >キャンセル</button>
            </div>
        </>
    )
}