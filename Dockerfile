FROM golang:1.19 AS fswatcher-build

WORKDIR /usr/src/app
COPY . .

RUN go build -o pod-babashka-fswatcher main.go

FROM babashka/babashka:0.8.157

WORKDIR "/atm/home"
COPY script.clj . 
COPY --from=fswatcher-build /usr/src/app/pod-babashka-fswatcher .
ENTRYPOINT ["bb", "script.clj"]
