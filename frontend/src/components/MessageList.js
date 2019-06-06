import React, {Component} from 'react'
import axios from 'axios'
import Message from "./Message";
import '../style.css'

class MessageList extends Component {
    constructor() {
        super();

        this.state = {
            messageList: [],
            interval: 0
        };

        this.showMessages = this.showMessages.bind(this)
        this.setMessages = this.setMessages.bind(this)
    }

    componentWillMount() {
        this.interval = setInterval(() =>
            {this.showMessages();}, 1000);
    }

    componentWillUnmount() {
        clearInterval(this.interval);
    }

    setMessages(messageList) {
        this.setState({
            messageList: messageList
        });
    }

    showMessages() {
        var self = this;
        axios.get(`https://omami.herokuapp.com/messages?userId=${this.props.userId}`
        ).then(function (response) {
            for( var i = 0; i < response.data.length; i++){
                const currRoom = self.props.currentRoom;
                const msgRoomId = response.data[i].roomId;
                if (msgRoomId != currRoom) {
                    response.data.splice(i, 1);
                    i--;
                }
            }
            self.setMessages(response.data);
            self.props.loadMessages(response.data)
        }).catch(function (error) {
            console.log(error.message);
        });
    };

    render() {
        return (
            <div className="message-list">
                {this.props.messages.map((item, index) => {
                    return (
                        <Message key={index} username={item.username} text={item.data}/>
                    )
                })}
            </div>
        )
    }
}

export default MessageList