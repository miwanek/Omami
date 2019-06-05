import React, {Component} from 'react'
import axios from 'axios'
import Message from "./Message";

class NewRoomForm extends Component {
    constructor() {
        super()
        this.state = {
            room: ''
        }
        this.handleChange = this.handleChange.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleChange(e) {
        this.setState({
            room: e.target.value
        })
    }

    handleSubmit(e) {
        e.preventDefault()
        axios.post(`https://omami.herokuapp.com/rooms?name=${this.state.room}`
        ).then(function (response) {
            console.log("Room created");
        }).catch(function (error){
            console.log(error.message);
        });

        this.setState({
            room: ''
        })
    }

    render() {
        return (
            <div className="new-room-form">
                <form onSubmit={this.handleSubmit}>
                    <input
                        onChange={this.handleChange}
                        type="text"
                        value={this.state.room}
                        placeholder="New room form"
                        required />
                    <button className="create-room-btn" type="submit">+</button>
                </form>
            </div>
        )
    }
}
export default NewRoomForm
