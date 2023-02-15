package br.com.fiap.listinha.rabbitmq

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


object EmailSender {
    fun enviarEmail(destinatario: String?, assunto: String?, mensagem: String?) {
        val username = "seu-email@gmail.com" // Coloque aqui o endereço de e-mail que será usado para enviar a mensagem
        val password = "sua-senha" // Coloque aqui a senha do endereço de e-mail

        // Configuração do servidor SMTP do Gmail
        val props = Properties()
        props.put("mail.smtp.auth", "true")
        props.put("mail.smtp.starttls.enable", "true")
        props.put("mail.smtp.host", "smtp.gmail.com")
        props.put("mail.smtp.port", "587")

        // Cria uma sessão de e-mail com autenticação
        val session = Session.getInstance(props, object : Authenticator() {
            override fun getPasswordAuthentication(): PasswordAuthentication {
                return PasswordAuthentication(username, password)
            }
        })
        try {
            // Cria uma mensagem de e-mail
            val message: Message = MimeMessage(session)
            message.setFrom(InternetAddress(username))
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario))
            message.subject = assunto
            message.setText(mensagem)

            // Envia a mensagem de e-mail
            Transport.send(message)
            println("Mensagem enviada com sucesso.")
        } catch (e: MessagingException) {
            throw RuntimeException(e)
        }
    }
}



