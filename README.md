# 🗓️ TáRolando – Sistema de Eventos (Java Console, Maven)

**TáRolando** é um sistema de console desenvolvido em Java com Maven, que permite o **cadastro de usuários** e a **gerência de eventos** locais. Ele foi criado como parte da atividade **Pratique – Imersão Digital** e segue os princípios da programação orientada a objetos.

---

## 🎯 Objetivo

A aplicação simula uma plataforma de eventos que permite ao usuário:

- Cadastrar eventos com data, hora, local, descrição e categoria.
- Cadastrar usuários com dados personalizados.
- Participar e cancelar participação em eventos.
- Ver eventos que já ocorreram, que estão em andamento e futuros.
- Armazenar e carregar dados de forma persistente usando arquivos `.json` e `.data`.

---

## 🚀 Funcionalidades

- [ ] Sistema de cadastro de usuários
- [ ] Participação e cancelamento de eventos
- [x] Cadastro completo de eventos
- [x] Enum fixo para categorias (FESTAS, SHOWS, etc.)
- [x] Identificação de eventos passados, presentes e futuros
- [x] Ordenação por horário com `LocalDateTime`
- [x] Armazenamento persistente: `usuarios.json` e `events.data`

---

## ⚙️ Tecnologias

- Java 17
- Maven
- Gson (persistência em JSON)
- `java.time` (`LocalDateTime`)

---

### 1. Para rodar:
```bash
git clone https://github.com/mar1nho/trabalho-faculdade-javacli.git
cd tarolando
mvn compile
mvn exec:java -Dexec.mainClass="Main"
```
