kafka-topics --bootstrap-server localhost:19092 --create --topic shelter-created --partitions 3  --config max.message.bytes=64000 --config flush.messages=1
kafka-topics --bootstrap-server localhost:19092 --create --topic dog-created --partitions 3  --config max.message.bytes=64000 --config flush.messages=1
