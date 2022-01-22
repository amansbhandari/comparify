from flask import Flask

server_port = 8000

app = Flask(__name__)


@app.route("/", methods=['GET'])
def hello():
    return "It works!"


if __name__ == "__main__":
    app.run(host='0.0.0.0', port = server_port)