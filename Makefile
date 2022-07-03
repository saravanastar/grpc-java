.PHONY: create_java_file_from_proto


create_java_file_from_proto:
	protoc --plugin=protoc-gen-grpc-java --proto_path=src/main/proto --java_out=src/main/java src/main/proto/user.proto