import React, {Component} from 'react'
import axios from 'axios'
import NewRoomForm from "./NewRoomForm";

class SendMessageForm extends Component {
    constructor() {
        super()
        this.state = {
            message: ''
        }
        this.handleChange = this.handleChange.bind(this)
        this.handleSubmit = this.handleSubmit.bind(this)
    }

    handleChange(e) {
        this.setState({
            message: e.target.value
        })
    }

    handleSubmit(e) {
        e.preventDefault()
        //this.props.sendMessage("first", 1, this.state.message)
        var self = this;
        axios.post(`http://localhost:5000/messages`, {
            userId: this.props.userId,
            roomId: this.props.currentRoom,
            data: this.state.message
        }).then(function (response) {
            console.log("Message sent");
        }).catch(function (error) {
            console.log(error.message);
        });

        this.setState({
            message: ''
        })
    }

    render() {
        return (
            <form
                onSubmit={this.handleSubmit}
                className="send-message-form">
                <input
                    onChange={this.handleChange}
                    value={this.state.message}
                    placeholder="Type your message and hit ENTER"
                    type="text" />
            </form>
        )
    }
}

export default SendMessageForm
