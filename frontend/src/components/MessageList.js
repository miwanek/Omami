import React, {Component} from 'react'
import axios from 'axios'
import Message from "./Message";
import '../style.css'

const tempData = [
    {
        senderId: 'one',
        text: 'Hello everyone'
    },
    {
        senderId: 'two',
        text: 'Hello'
    },
    {
        senderId: 'three',
        text: 'Well, hi'
    },
]

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
            {
                this.showMessages();
            }, 1000);
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
        axios.get(`http://localhost:5000/messages?userId=${this.props.userId}`
        ).then(function (response) {
            for( var i = 0; i < response.data.length; i++){
                if ( !(response.data[i].roomId === this.props.currentRoom)) {
                    response.data.splice(i, 1);
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